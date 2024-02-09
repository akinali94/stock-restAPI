package com.nttdatacasefirst.stockAPI.mapper;

import com.nttdatacasefirst.stockAPI.dtos.StockAddModel;
import com.nttdatacasefirst.stockAPI.dtos.StockGetModel;
import com.nttdatacasefirst.stockAPI.entity.Stock;

import java.util.List;

public interface MapperStock {
    StockGetModel toModelGet(Stock stock);
    List<StockGetModel> toModelGetList(List<Stock> stocks);

}
