package com.nttdatacasefirst.stockAPI.mapper.impl;

import com.nttdatacasefirst.stockAPI.dtos.OperationAddModel;
import com.nttdatacasefirst.stockAPI.dtos.OperationGetModel;
import com.nttdatacasefirst.stockAPI.entity.Operation;
import com.nttdatacasefirst.stockAPI.mapper.MapperOperation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperOperationImpl implements MapperOperation {

    @Override
    public OperationGetModel toModelGet(Operation operation) {

        return new OperationGetModel(
                operation.getOperationType().getLabel(),
                operation.getStock().getCapitalIncrease().getArrangementNo(),
                operation.getStock().getSerialNo(),
                operation.getDate(),
                operation.getDividentYear(),
                operation.getDividendTotal(),
                operation.getStock().getShareHolder().getTitle()
        );
    }

    @Override
    public List<OperationGetModel> toModelGetList(List<Operation> operationList) {

        return operationList.stream().map(x -> new OperationGetModel(
                    x.getOperationType().getLabel(),
                    x.getStock().getCapitalIncrease().getArrangementNo(),
                    x.getStock().getSerialNo(),
                    x.getDate(),
                    x.getDividentYear(),
                    x.getDividendTotal(),
                    x.getStock().getShareHolder().getTitle()))
                .collect(Collectors.toList());
    }
}
