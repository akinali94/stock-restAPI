package com.nttdatacasefirst.stockAPI.mapper.impl;

import com.nttdatacasefirst.stockAPI.dtos.StockGetModel;
import com.nttdatacasefirst.stockAPI.entity.ShareHolder;
import com.nttdatacasefirst.stockAPI.entity.Stock;
import com.nttdatacasefirst.stockAPI.mapper.MapperStock;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperStockImpl implements MapperStock {

    @Override
    public StockGetModel toModelGet(Stock stock) {
        StockGetModel getStock = new StockGetModel();

        getStock.setId(stock.getId());
        getStock.setCapitalIncreaseArrNo(stock.getCapitalIncrease().getArrangementNo());
        getStock.setSerialNo(stock.getSerialNo());
        getStock.setNominalValue(stock.getNominalValue());
        getStock.setShareholder(stock.getShareHolder() == null ? "null" : stock.getShareHolder().getTitle() );

        return getStock;
    }

    @Override
    public List<StockGetModel> toModelGetList(List<Stock> stockList) {
        return stockList.stream()
                .map(x -> {
                    ShareHolder sHolder = x.getShareHolder();
                    String titleGet = sHolder == null ? "null" : sHolder.getTitle();
                    return new StockGetModel(
                            x.getId(),
                            x.getCapitalIncrease().getArrangementNo(),
                            x.getSerialNo(),
                            x.getNominalValue(),
                            titleGet);
                })
                .collect(Collectors.toList());
    }
}
