package com.nttdatacasefirst.stockAPI.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockGetModel {
    private Long Id;
    private Long capitalIncreaseArrNo;
    private int serialNo;
    private BigDecimal nominalValue;
    private String Shareholder;
}