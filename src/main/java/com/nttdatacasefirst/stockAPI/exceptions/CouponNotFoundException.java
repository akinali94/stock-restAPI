package com.nttdatacasefirst.stockAPI.exceptions;

public class CouponNotFoundException extends RuntimeException{
    public CouponNotFoundException(String message) {
        super(message);
    }
}
