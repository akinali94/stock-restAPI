package com.nttdatacasefirst.stockAPI.dtos;

import com.nttdatacasefirst.stockAPI.entity.Stock;
import com.nttdatacasefirst.stockAPI.entity.enums.CouponType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CouponAddModel {
    private CouponType couponType;
    private int arrangementNo;
    private int clippingNo;
    private int yearNo;
    private boolean isUsed;
    private Stock stock;
}
