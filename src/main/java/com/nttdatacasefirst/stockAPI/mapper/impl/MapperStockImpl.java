package com.nttdatacasefirst.stockAPI.mapper.impl;

import com.nttdatacasefirst.stockAPI.dtos.StockAddModel;
import com.nttdatacasefirst.stockAPI.dtos.StockGetModel;
import com.nttdatacasefirst.stockAPI.entity.Stock;
import com.nttdatacasefirst.stockAPI.mapper.MapperStock;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperStockImpl implements MapperStock {

    @Override
    public Stock toAdd(StockAddModel addModel) {
        Stock newStock = new Stock();
        newStock.setNominalValue(addModel.getNominalValue());
        newStock.setCapitalIncrease(addModel.getCapitalIncrease());

        return newStock;
    }

    @Override
    public StockGetModel toModelGet(Stock stock) {
        StockGetModel getStock = new StockGetModel();

        getStock.setCapitalIncreaseArrNo(getStock.getCapitalIncreaseArrNo());
        getStock.setSerialNo(getStock.getSerialNo());
        getStock.setNominalValue(getStock.getNominalValue());
        getStock.setShareholder(getStock.getShareholder());

        return getStock;
    }

    @Override
    public List<StockGetModel> toModelGetList(List<Stock> stockList) {
        return stockList.stream()
                .map(x -> new StockGetModel(
                        x.getCapitalIncrease().getArrangementNo(),
                        x.getSerialNo(),
                        x.getNominalValue(),
                        x.getShareHolder().getTitle()))
                .collect(Collectors.toList());
    }
}
