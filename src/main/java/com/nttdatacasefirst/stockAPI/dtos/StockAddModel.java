package com.nttdatacasefirst.stockAPI.dtos;

import com.nttdatacasefirst.stockAPI.entity.CapitalIncrease;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockAddModel {

    private String capitalIncreaseArrNo;
    private BigDecimal nominalValue;

}
