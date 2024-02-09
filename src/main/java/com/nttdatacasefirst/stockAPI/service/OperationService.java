package com.nttdatacasefirst.stockAPI.service;

import com.nttdatacasefirst.stockAPI.dtos.OperationAddModel;
import com.nttdatacasefirst.stockAPI.dtos.OperationGetModel;
import com.nttdatacasefirst.stockAPI.entity.Stock;

import java.util.List;

public interface OperationService {

    OperationGetModel addOperation(OperationAddModel addModel);

    List<OperationGetModel> getAllOperations();

    /*void deleteOperationOfStocks(Stock stock);*/
}
