package com.nttdatacasefirst.stockAPI.service;

import com.nttdatacasefirst.stockAPI.dtos.OperationAddModel;
import com.nttdatacasefirst.stockAPI.dtos.OperationGetModel;

import java.util.List;

public interface OperationService {

    OperationGetModel addOperation(OperationAddModel addModel);

    List<OperationGetModel> getAllOperations();
}
