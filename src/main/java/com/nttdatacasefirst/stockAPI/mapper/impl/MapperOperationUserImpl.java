package com.nttdatacasefirst.stockAPI.mapper.impl;

import com.nttdatacasefirst.stockAPI.dtos.OperationUserGetModel;
import com.nttdatacasefirst.stockAPI.entity.Operation;
import com.nttdatacasefirst.stockAPI.mapper.MapperOperationUser;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperOperationUserImpl implements MapperOperationUser {
    @Override
    public OperationUserGetModel toModelGet(Operation operationUser) {
        OperationUserGetModel newModel = new OperationUserGetModel();
        newModel.setOperationType(operationUser.getOperationType().getLabel());
        newModel.setCapitalIncreaseArrNo(operationUser.getStock().getCapitalIncrease().getArrangementNo());
        newModel.setStockSerialNo(operationUser.getStock().getSerialNo());
        newModel.setOperationDate(operationUser.getDate());
        newModel.setDividentYear(operationUser.getDividentYear());
        newModel.setDividentTotal(operationUser.getDividendTotal());
        newModel.setShareholderTitle(operationUser.getShareHolder() == null ? "null" : operationUser.getShareHolder().getTitle());

        return newModel;
    }

    @Override
    public List<OperationUserGetModel> toModelGet(List<Operation> operationUser) {
        return operationUser.stream()
                .map(x -> {
                    String title = x.getShareHolder() == null ? "null" : x.getShareHolder().getTitle();
                    return new OperationUserGetModel(
                            x.getOperationType().getLabel(),
                            x.getStock().getCapitalIncrease().getArrangementNo(),
                            x.getStock().getSerialNo(),
                            x.getDate(),
                            x.getDividentYear(),
                            x.getDividendTotal(),
                            title);
                })
                .collect(Collectors.toList());
    }
}
