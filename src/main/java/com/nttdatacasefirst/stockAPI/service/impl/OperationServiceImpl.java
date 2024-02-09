package com.nttdatacasefirst.stockAPI.service.impl;

import com.nttdatacasefirst.stockAPI.dtos.OperationAddModel;
import com.nttdatacasefirst.stockAPI.dtos.OperationGetModel;
import com.nttdatacasefirst.stockAPI.entity.*;
import com.nttdatacasefirst.stockAPI.entity.enums.OperationType;
import com.nttdatacasefirst.stockAPI.exceptions.CapitalIncreaseNotFoundException;
import com.nttdatacasefirst.stockAPI.exceptions.DividendDistributionNotFoundException;
import com.nttdatacasefirst.stockAPI.exceptions.OperationNotFoundException;
import com.nttdatacasefirst.stockAPI.exceptions.ShareholderNotFoundException;
import com.nttdatacasefirst.stockAPI.mapper.MapperOperation;
import com.nttdatacasefirst.stockAPI.repository.OperationRepository;
import com.nttdatacasefirst.stockAPI.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {
    private final OperationRepository repositoryOperation;
    private final MapperOperation mapperOperation;
    private final StockService serviceStock;
    private final ShareholderService serviceShareholder;
    private final DividendDistributionService serviceDividendDistribution;
    private final CapitalIncreaseService serviceCapitalIncrease;

    public OperationServiceImpl(@Autowired OperationRepository repositoryOperation,
                                @Autowired MapperOperation mapperOperation,
                                @Autowired StockService serviceStock,
                                @Autowired ShareholderService serviceShareholder,
                                @Autowired DividendDistributionService serviceDividendDistribution,
                                @Autowired CapitalIncreaseService serviceCapitalIncrease) {
        this.repositoryOperation = repositoryOperation;
        this.mapperOperation = mapperOperation;
        this.serviceStock = serviceStock;
        this.serviceShareholder = serviceShareholder;
        this.serviceDividendDistribution = serviceDividendDistribution;
        this.serviceCapitalIncrease = serviceCapitalIncrease;
    }

    public OperationGetModel addOperation(OperationAddModel addModel){
        //Find Stock
        Stock findStock = serviceStock.getStockById(addModel.getStockID());
        //Find Shareholder
        ShareHolder findShareholder = serviceShareholder.findShareholderForOperations(addModel.getShareholderNo());
        //Find CapitalIncrease
        CapitalIncrease capitalIncrease = serviceCapitalIncrease
                .findCapitalIncrease(findStock.getCapitalIncrease().getArrangementNo())
                .orElseThrow(() -> new CapitalIncreaseNotFoundException("Capital Increase Not Found in Operations"));

        //new Operation
        Operation newOperation = new Operation();

        //Stock Operations
        if(addModel.getOperationType().equals("STOCK")){

            newOperation.setOperationType(OperationType.valueOf(addModel.getOperationType()));
            newOperation.setStock(findStock);
            newOperation.setDate(new Date());
            newOperation.setShareHolder(findShareholder);

            //Change coupon as used
            serviceStock.changeCouponToUsedInStockOperation(findStock);

            //Set Shareholder of Stock
            serviceStock.changeShareholderofStock(findStock, findShareholder);
        }

        //Dividend Operations
        if(addModel.getOperationType().equals("DIVIDEND")){

            //Check DividendDistribution
            List<DividendDistribution> div = serviceDividendDistribution
                    .getDividendDistributionByCapitalIncrementArrNo(capitalIncrease.getArrangementNo());
            if(div.isEmpty())
                throw new DividendDistributionNotFoundException("This capital Increase do not have any Dividend");
            //Find last DividendDistribution for this Capital Increase
            DividendDistribution maxSerialNoDividend = div.stream()
                    .max(Comparator.comparing(DividendDistribution::getSerialNo))
                    .orElseThrow(() ->
                            new DividendDistributionNotFoundException("Dividend Dist Not Found when search max Serial No"));

            //Set DividendTotal
            double division = (double) maxSerialNoDividend.getDividentRate() / 100;
            BigDecimal getNominal = findStock.getNominalValue();
            BigDecimal divRate = new BigDecimal(division);
            BigDecimal divTotal = getNominal.multiply(divRate);


            newOperation.setOperationType(OperationType.valueOf(addModel.getOperationType()));

            newOperation.setStock(findStock);
            newOperation.setDividendDistribution(maxSerialNoDividend);
            newOperation.setDate(new Date());
            newOperation.setShareHolder(findShareholder);
            newOperation.setDividentYear(maxSerialNoDividend.getDividendYear());
            newOperation.setDividendTotal(divTotal);

            serviceStock.changeCouponToUsedInDividdentOperation(findStock);
        }

        Operation createdOperation = repositoryOperation.save(newOperation);

        return mapperOperation.toModelGet(createdOperation);
    }

    @Override
    public List<OperationGetModel> getAllOperations() {

        List<Operation> getAll = repositoryOperation.findAll();
        if(getAll.isEmpty())
            throw new OperationNotFoundException("There is no operation");

        return mapperOperation.toModelGetList(getAll);
    }

/*    @Override
    public void deleteOperationOfStocks(Stock stock){
        List<Operation> findOperationsByStock = repositoryOperation.findAll()
                .stream()
                .filter(x -> x.getStock() == stock)
                .toList();

        repositoryOperation.deleteAll(findOperationsByStock);
    }*/

}