package com.nttdatacasefirst.stockAPI.service.impl;

import com.nttdatacasefirst.stockAPI.dtos.CouponGetModel;
import com.nttdatacasefirst.stockAPI.entity.CapitalIncrease;
import com.nttdatacasefirst.stockAPI.entity.Coupon;
import com.nttdatacasefirst.stockAPI.entity.Stock;
import com.nttdatacasefirst.stockAPI.entity.enums.CouponType;
import com.nttdatacasefirst.stockAPI.exceptions.CouponNotFoundException;
import com.nttdatacasefirst.stockAPI.repository.CouponRepository;
import com.nttdatacasefirst.stockAPI.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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
        int clippingNo = arrNo.intValue();

        for(int i = 0; i<16; i++){
            Coupon newCoupon = new Coupon();

            newCoupon.setCouponNo(i+1);
            newCoupon.setType(CouponType.INTEREST);
            newCoupon.setArrangementNo(arrNo);
            newCoupon.setClippingNo(clippingNo);
            newCoupon.setUsed(false);
            newCoupon.setStockNo(stock);
            clippingNo++;
            repositoryCoupon.save(newCoupon);
        }

        for(int i = 0; i<10; i++){
            Coupon newCoupon = new Coupon();

            newCoupon.setCouponNo(i+17);
            newCoupon.setType(CouponType.DIVIDEND);
            newCoupon.setArrangementNo(arrNo);
            newCoupon.setYearNo(year);
            newCoupon.setUsed(false);
            newCoupon.setStockNo(stock);
            year++;
            repositoryCoupon.save(newCoupon);
        }
    }

    @Override
    public Coupon findNotUsedAndMinClippingNoCoupon(Stock stock) {
        return stock.getCouponList().stream().filter(x -> !x.isUsed()).min(Comparator.comparing(Coupon::getClippingNo))
                .orElseThrow(() -> new CouponNotFoundException("Coupon Not Found"));
    }

    @Override
    public Coupon findNotUsedandMinDividendYear(Stock stock) {
        return stock.getCouponList().stream().filter(x -> !x.isUsed()).min(Comparator.comparing(Coupon::getYearNo))
                .orElseThrow(() -> new CouponNotFoundException("Coupon Not Found"));
    }

    @Override
    public List<CouponGetModel> getCouponByArrangementNo(Long arrNo) {
        return null;
    }

}