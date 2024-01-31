package com.nttdatacasefirst.stockAPI.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShareholderAddModel {
    private Long regNo;
    private String title;
    private String address;
    private String phoneNo;
    private String InvestorType;
}
