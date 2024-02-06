package com.nttdatacasefirst.stockAPI.service;

import com.nttdatacasefirst.stockAPI.dtos.CouponGetModel;
import com.nttdatacasefirst.stockAPI.entity.Coupon;
import com.nttdatacasefirst.stockAPI.entity.Stock;

import java.util.List;

public interface CouponService {

    void AddCouponFromStockService(Stock stock);
    //CouponGetModel addCoupon(CouponAddModel addModel);

    Coupon findNotUsedAndMinClippingNoCoupon(Stock stock);
    Coupon findNotUsedandMinDividendYear(Stock stock);
    List<CouponGetModel> getAllCoupon();
    List<CouponGetModel> getCouponByStockId(String id);

}
