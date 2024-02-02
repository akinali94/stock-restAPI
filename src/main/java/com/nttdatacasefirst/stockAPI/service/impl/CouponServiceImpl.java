package com.nttdatacasefirst.stockAPI.service.impl;

import com.nttdatacasefirst.stockAPI.dtos.CouponGetModel;
import com.nttdatacasefirst.stockAPI.entity.CapitalIncrease;
import com.nttdatacasefirst.stockAPI.entity.Coupon;
import com.nttdatacasefirst.stockAPI.entity.Stock;
import com.nttdatacasefirst.stockAPI.entity.enums.CouponType;
import com.nttdatacasefirst.stockAPI.repository.CouponRepository;
import com.nttdatacasefirst.stockAPI.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {

    private final CouponRepository repositoryCoupon;

    public CouponServiceImpl(@Autowired CouponRepository repositoryCoupon) {
        this.repositoryCoupon = repositoryCoupon;
    }

    @Override
    public void AddCouponFromStockService(Stock stock) {
        Long arrNo = stock.getCapitalIncrease().getArrangementNo();
        int year = stock.getCapitalIncrease().getYear();

        for(int i = 0; i<16; i++){
            Coupon newCoupon = new Coupon();
            int clippingNo = arrNo.intValue();
            newCoupon.setType(CouponType.INTEREST);
            newCoupon.setArrangementNo(arrNo);
            newCoupon.setClippingNo(clippingNo++);
            newCoupon.setUsed(false);
            newCoupon.setStockNo(stock);

            repositoryCoupon.save(newCoupon);
        }

        for(int i = 0; i<10; i++){
            Coupon newCoupon = new Coupon();
            int getYear = year;
            newCoupon.setType(CouponType.DIVIDEND);
            newCoupon.setArrangementNo(arrNo);
            newCoupon.setYearNo(getYear++);
            newCoupon.setUsed(false);
            newCoupon.setStockNo(stock);

            repositoryCoupon.save(newCoupon);
        }
    }

    @Override
    public List<CouponGetModel> getCouponByArrangementNo(Long arrNo) {
        return null;
    }
}
