package com.nttdatacasefirst.stockAPI.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DividendDistributionAddModel {
    private Long capitalIncreaseArrNo;
    private int dividendRate;
    private int dividendYear;
}
