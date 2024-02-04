package com.nttdatacasefirst.stockAPI.service.impl;

import com.nttdatacasefirst.stockAPI.dtos.OperationAddModel;
import com.nttdatacasefirst.stockAPI.dtos.OperationGetModel;
import com.nttdatacasefirst.stockAPI.entity.*;
import com.nttdatacasefirst.stockAPI.entity.enums.OperationType;
import com.nttdatacasefirst.stockAPI.exceptions.StockNotFoundException;
import com.nttdatacasefirst.stockAPI.mapper.MapperOperation;
import com.nttdatacasefirst.stockAPI.repository.OperationsRepository;
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
    private final OperationsRepository repositoryOperation;
    private final MapperOperation mapperOperation;
    private final StockService serviceStock;
    private final ShareholderService serviceShareholder;
    private final DividendDistributionService serviceDividendDistribution;

    public OperationServiceImpl(@Autowired OperationsRepository repositoryOperation,
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
            //Check DividentDistribution
            DividendDistribution div = serviceDividendDistribution
                    .getDividendDistributionByCapitalIncrementArrNo(addModel.getArrangementNo());

            //Find available Stock
            Stock availableStock = serviceStock.getAvailableStockForDividendOperation(stockList, findShareholder);

            newOperation.setStock(availableStock);
            newOperation.setDate(new Date());
            newOperation.setShareHolder(findShareholder);
            newOperation.setDividentYear(div.getDividendYear());
            newOperation.setDividendTotal(availableStock.getNominalValue().intValue() * div.getDividentRate() / 100);

        }

        repositoryOperation.save(newOperation);

        return mapperOperation.toModelGet(newOperation);
    }

}