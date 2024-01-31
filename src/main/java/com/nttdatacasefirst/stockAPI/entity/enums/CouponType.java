package com.nttdatacasefirst.stockAPI.entity.enums;

public enum CouponType {
    DIVIDEND(1L, "DIVIDEND"), //kar payi kuponu (temettu)
    INTEREST(2L, "INTEREST"); //pay alma kuponu
    private final Long value;
    private final String label;

    CouponType(Long value, String label){
        this.value = value;
        this.label = label;
    }
    public Long getValue() { return value; }
    public String getLabel() { return label;}

}
