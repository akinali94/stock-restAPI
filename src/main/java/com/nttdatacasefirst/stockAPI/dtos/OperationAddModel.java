package com.nttdatacasefirst.stockAPI.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationAddModel {
    private String operationType;
    private String StockID;
    private String shareholderNo;

}
