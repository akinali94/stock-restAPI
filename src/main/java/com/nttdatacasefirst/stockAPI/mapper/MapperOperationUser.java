package com.nttdatacasefirst.stockAPI.mapper;

import com.nttdatacasefirst.stockAPI.dtos.OperationUserGetModel;
import com.nttdatacasefirst.stockAPI.entity.Operation;

import java.util.List;

public interface MapperOperationUser {
    OperationUserGetModel toModelGet(Operation operationUser);

    List<OperationUserGetModel> toModelGet(List<Operation> operationUser);
}
