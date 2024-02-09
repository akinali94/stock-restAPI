package com.nttdatacasefirst.stockAPI.dtos;

import lombok.*;

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
    @NonNull
    private String Shareholder;
}