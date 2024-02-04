package com.nttdatacasefirst.stockAPI.dtos;

import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CapitalIncreaseAddModel {
    @Digits(integer = 15, fraction = 2)
    private BigDecimal rightIssue;
    @Digits(integer = 15, fraction = 2)
    private BigDecimal bonusIssue;
    private int increasingRate;

}
