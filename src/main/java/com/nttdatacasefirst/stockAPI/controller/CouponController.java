package com.nttdatacasefirst.stockAPI.controller;

import com.nttdatacasefirst.stockAPI.dtos.CouponGetModel;
import com.nttdatacasefirst.stockAPI.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/coupon")
public class CouponController {
    private final CouponService service;

    public CouponController(@Autowired CouponService service) {
        this.service = service;
    }

    @GetMapping("/search/all")
    public ResponseEntity<List<CouponGetModel>> getAllCoupons(){
        return ResponseEntity.ok(service.getAllCoupon());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<List<CouponGetModel>> getCouponsByStockId(@PathVariable String id){
        return ResponseEntity.ok(service.getCouponByStockId(id));
    }


}
