package com.nttdatacasefirst.stockAPI.mapper.impl;

import com.nttdatacasefirst.stockAPI.dtos.CouponGetModel;
import com.nttdatacasefirst.stockAPI.entity.Coupon;
import com.nttdatacasefirst.stockAPI.mapper.MapperCoupon;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperCouponImpl implements MapperCoupon {
    @Override
    public CouponGetModel toModelGet(Coupon coupon) {
        CouponGetModel getCoupon = new CouponGetModel();

        getCoupon.setCouponType(coupon.getType().getLabel());
        getCoupon.setArrangementNo(coupon.getArrangementNo());
        getCoupon.setClippingNo(coupon.getClippingNo());
        getCoupon.setYearNo(coupon.getYearNo());
        getCoupon.setUsed(coupon.isUsed());

        return getCoupon;
    }

    @Override
    public List<CouponGetModel> toModelGetList(List<Coupon> couponList) {

        return couponList.stream()
                .map(x -> new CouponGetModel(
                        x.getType().getLabel(),
                        x.getArrangementNo(),
                        x.getCouponNo(),
                        x.getClippingNo(),
                        x.getYearNo(),
                        x.isUsed()))
                .collect(Collectors.toList());
    }
}