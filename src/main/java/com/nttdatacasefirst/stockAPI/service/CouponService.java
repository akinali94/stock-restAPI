package com.nttdatacasefirst.stockAPI.service;

import com.nttdatacasefirst.stockAPI.dtos.CouponAddModel;
import com.nttdatacasefirst.stockAPI.dtos.CouponGetModel;
import com.nttdatacasefirst.stockAPI.entity.Stock;

import java.util.List;

public interface CouponService {

    void AddCouponFromStockService(Stock stock);
    //CouponGetModel addCoupon(CouponAddModel addModel);
    List<CouponGetModel> getCouponByArrangementNo(Long arrNo);
}
