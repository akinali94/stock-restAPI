package com.nttdatacasefirst.stockAPI.entity;

import com.nttdatacasefirst.stockAPI.entity.enums.CouponType;
import jakarta.persistence.*;

@Entity
public class Coupon {

    @Id
    @GeneratedValue
    private int Id;
/*    @ManyToOne
    private CapitalIncrease arrangementNo;*/
    private CouponType type;
    @ManyToOne
    private Stock stockNo;
    private int clippingNo; //kupur numarasi - pay alma
    private int yearNo; //kar payi kuponu
    private boolean isUsed;

}
