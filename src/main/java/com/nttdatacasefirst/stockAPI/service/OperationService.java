package com.nttdatacasefirst.stockAPI.service;

import com.nttdatacasefirst.stockAPI.dtos.OperationAddModel;
import com.nttdatacasefirst.stockAPI.dtos.OperationGetModel;

public interface OperationService {

    OperationGetModel addOperation(OperationAddModel addModel);
}
