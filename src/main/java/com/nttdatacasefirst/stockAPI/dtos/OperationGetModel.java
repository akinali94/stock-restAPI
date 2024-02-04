package com.nttdatacasefirst.stockAPI.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationGetModel {
    private String operationType;
    private Long capitalIncreaseArrNo;
    private int stockSerialNo;
    private Date operationDate;
    private int dividentYear;
    private int dividentTotal;
    private String ShareholderTitle;
}
