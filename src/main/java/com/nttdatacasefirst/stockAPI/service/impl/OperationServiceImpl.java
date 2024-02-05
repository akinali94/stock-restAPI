package com.nttdatacasefirst.stockAPI.service.impl;

import com.nttdatacasefirst.stockAPI.dtos.OperationAddModel;
import com.nttdatacasefirst.stockAPI.dtos.OperationGetModel;
import com.nttdatacasefirst.stockAPI.entity.*;
import com.nttdatacasefirst.stockAPI.entity.enums.OperationType;
import com.nttdatacasefirst.stockAPI.exceptions.DividendDistributionNotFoundException;
import com.nttdatacasefirst.stockAPI.exceptions.OperationNotFoundException;
import com.nttdatacasefirst.stockAPI.mapper.MapperOperation;
import com.nttdatacasefirst.stockAPI.repository.OperationRepository;
import com.nttdatacasefirst.stockAPI.service.DividendDistributionService;
import com.nttdatacasefirst.stockAPI.service.OperationService;
import com.nttdatacasefirst.stockAPI.service.ShareholderService;
import com.nttdatacasefirst.stockAPI.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public OperationServiceImpl(@Autowired OperationRepository repositoryOperation,
                                @Autowired MapperOperation mapperOperation,
                                @Autowired StockService serviceStock,
                                @Autowired ShareholderService serviceShareholder,
                                @Autowired DividendDistributionService serviceDividendDistribution) {
        this.repositoryOperation = repositoryOperation;
        this.mapperOperation = mapperOperation;
        this.serviceStock = serviceStock;
        this.serviceShareholder = serviceShareholder;
        this.serviceDividendDistribution = serviceDividendDistribution;
    }

    public OperationGetModel addOperation(OperationAddModel addModel){
        //Find Stock by CapitalIncrease
        List<Stock> stockList = serviceStock.getStocksofCapitalIncrement(addModel.getArrangementNo());
        //Find Shareholder
        ShareHolder findShareholder = serviceShareholder.getShareholderForOperations(addModel.getShareholderNo());

        //new Operation
        Operation newOperation = new Operation();

        //Set OperationType
        newOperation.setOperationType(OperationType.valueOf(addModel.getOperationType()));

        //Stock Operations
        if(addModel.getOperationType().equals("STOCK")){
            Stock availableStock = serviceStock.getAvailableStockForStockOperation(stockList);
            newOperation.setStock(availableStock);
            newOperation.setDate(new Date());
            newOperation.setShareHolder(findShareholder);

            //Change coupon as used
            serviceStock.changeCouponToUsedInStockOperation(availableStock);
        }

        //Dividend Operations
        if(addModel.getOperationType().equals("DIVIDEND")){
            //Check DividendDistribution
            List<DividendDistribution> div = serviceDividendDistribution
                    .getDividendDistributionByCapitalIncrementArrNo(addModel.getArrangementNo());
            //Find last DividendDistribution for this Capital Increase
            DividendDistribution maxSerialNoDividend = div.stream()
                    .max(Comparator.comparing(DividendDistribution::getSerialNo))
                    .orElseThrow(() ->
                            new DividendDistributionNotFoundException("Dividend Dist Not Found when search max Serial No"));

            //Find available Stock
            Stock availableStock = serviceStock.getAvailableStockForDividendOperation(stockList, findShareholder);

            newOperation.setStock(availableStock);
            newOperation.setDate(new Date());
            newOperation.setShareHolder(findShareholder);
            newOperation.setDividentYear(maxSerialNoDividend.getDividendYear());
            newOperation.setDividendTotal(availableStock.getNominalValue().intValue() * maxSerialNoDividend.getDividentRate() / 100);

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

}