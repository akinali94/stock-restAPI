package com.nttdatacasefirst.stockAPI.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DividendDistributionGetModel {
    private Long CapitalIncreaseArrNo;
    private int dividendYear;
    private int serialNo;
    private int dividendRate;

}
