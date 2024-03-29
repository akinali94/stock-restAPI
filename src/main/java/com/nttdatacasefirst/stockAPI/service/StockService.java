package com.nttdatacasefirst.stockAPI.service;

import com.nttdatacasefirst.stockAPI.dtos.StockAddModel;
import com.nttdatacasefirst.stockAPI.dtos.StockGetModel;
import com.nttdatacasefirst.stockAPI.entity.ShareHolder;
import com.nttdatacasefirst.stockAPI.entity.Stock;

import java.util.List;

public interface StockService {

    StockGetModel addStock(StockAddModel addModel);

    List<StockGetModel> getStocksofCapitalIncrease(String arrNo);

    List<StockGetModel> getAllStocks();
    List<Stock> getStocksofCapitalIncrement(String arrNo);
    Stock getAvailableStockForStockOperation(List<Stock> stockList);
    void changeCouponToUsedInStockOperation(Stock stock);
    Stock getAvailableStockForDividendOperation(List<Stock> stockList, ShareHolder shareholder);
    void changeCouponToUsedInDividdentOperation(Stock stock);
    StockGetModel deleteStockById(String id);
    void changeShareholderofStock(Stock stock, ShareHolder shareHolder);

    Stock getStockById(String id);

    List<StockGetModel> getStocksofShareholder(Long regNo);

    //Silinecek
    List<Stock> getAllStockEntity();
}
