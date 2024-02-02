package com.nttdatacasefirst.stockAPI.service;

import com.nttdatacasefirst.stockAPI.dtos.StockAddModel;
import com.nttdatacasefirst.stockAPI.dtos.StockGetModel;

import java.util.List;

public interface StockService {

    StockGetModel addStock(StockAddModel addModel);

    List<StockGetModel> getStocksofCapitalIncrease(String arrNo);

    List<StockGetModel> getAllStocks();

}
