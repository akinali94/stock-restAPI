package com.nttdatacasefirst.stockAPI.service;

import com.nttdatacasefirst.stockAPI.dtos.OperationAddModel;
import com.nttdatacasefirst.stockAPI.dtos.OperationGetModel;
import com.nttdatacasefirst.stockAPI.dtos.OperationUserGetModel;
import com.nttdatacasefirst.stockAPI.entity.Operation;
import com.nttdatacasefirst.stockAPI.entity.ShareHolder;
import com.nttdatacasefirst.stockAPI.entity.Stock;

import java.util.List;

public interface OperationService {

    OperationGetModel addOperation(OperationAddModel addModel);

    List<OperationGetModel> getAllOperations();
    List<OperationGetModel> getOperationsOfShareholder(ShareHolder shareholder);
    List<OperationGetModel> getDividendOperationOfShareholder(ShareHolder shareHolder);
    OperationUserGetModel saveOperation(Operation operation);

    /*void deleteOperationOfStocks(Stock stock);*/
}
