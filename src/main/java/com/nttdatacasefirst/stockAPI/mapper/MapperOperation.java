package com.nttdatacasefirst.stockAPI.mapper;

import com.nttdatacasefirst.stockAPI.dtos.OperationAddModel;
import com.nttdatacasefirst.stockAPI.dtos.OperationGetModel;
import com.nttdatacasefirst.stockAPI.entity.Operation;

import java.util.List;

public interface MapperOperation {
    OperationGetModel toModelGet(Operation operation);
    List<OperationGetModel> toModelGetList(List<Operation> operationList);
}
