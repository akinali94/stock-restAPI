package com.nttdatacasefirst.stockAPI.dtos;

import com.nttdatacasefirst.stockAPI.entity.enums.CouponType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CouponGetModel {
    private String couponType;
    private Long arrangementNo;
    private int couponNo;
    private int clippingNo;
    private int yearNo;
    private boolean isUsed;
}
