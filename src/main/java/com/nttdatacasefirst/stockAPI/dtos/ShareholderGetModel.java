package com.nttdatacasefirst.stockAPI.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShareholderGetModel {
    private Long registerNo;
    private String title;
    private String address;
    private String phoneNo;
    private String investorType;

}
