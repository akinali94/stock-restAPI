package com.nttdatacasefirst.stockAPI.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CapitalIncreaseAddModel {
    private int year;
    private int rightIssue;
    private int bonusIssue;
    private byte increasingRate;

}
