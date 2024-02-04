package com.nttdatacasefirst.stockAPI.mapper;

import com.nttdatacasefirst.stockAPI.dtos.CouponGetModel;
import com.nttdatacasefirst.stockAPI.entity.Coupon;

import java.util.List;

public interface MapperCoupon {

    CouponGetModel toModelGet(Coupon coupon);
    List<CouponGetModel> toModelGetList(List<Coupon> coupon);
}
