package com.nttdatacasefirst.stockAPI.mapper.impl;

import com.nttdatacasefirst.stockAPI.dtos.OperationGetModel;
import com.nttdatacasefirst.stockAPI.entity.Operation;
import com.nttdatacasefirst.stockAPI.entity.ShareHolder;
import com.nttdatacasefirst.stockAPI.mapper.MapperOperation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperOperationImpl implements MapperOperation {

    @Override
    public OperationGetModel toModelGet(Operation operation) {
        OperationGetModel newModel = new OperationGetModel();
        newModel.setOperationType(operation.getOperationType().getLabel());
        newModel.setCapitalIncreaseArrNo(operation.getStock().getCapitalIncrease().getArrangementNo());
        newModel.setStockSerialNo(operation.getStock().getSerialNo());
        newModel.setOperationDate(operation.getDate());
        newModel.setDividentYear(operation.getDividentYear());
        newModel.setDividentTotal(operation.getDividendTotal());
        ShareHolder checkShareholder = operation.getShareHolder();
        String title = checkShareholder == null ? "null" : operation.getShareHolder().getTitle();
        newModel.setShareholderTitle(title);

        return newModel;
    }

    @Override
    public List<OperationGetModel> toModelGetList(List<Operation> operationList) {

        return operationList.stream()
                .map(x -> {
                    ShareHolder sHolder = x.getShareHolder();
                    String titleGet = sHolder == null ? "null" : sHolder.getTitle();
                    return new OperationGetModel(
                            x.getOperationType().getLabel(),
                            x.getStock().getCapitalIncrease().getArrangementNo(),
                            x.getStock().getSerialNo(),
                            x.getDate(),
                            x.getDividentYear(),
                            x.getDividendTotal(),
                            titleGet);
                })
                .collect(Collectors.toList());
    }
}
