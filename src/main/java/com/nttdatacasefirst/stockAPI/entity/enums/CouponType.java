package com.nttdatacasefirst.stockAPI.entity.enums;

public enum CouponType {
    DIVIDEND(1, "DIVIDEND"), //kar payi kuponu (temettu)
    INTEREST(2, "INTEREST"); //pay alma kuponu
    private final Integer value;
    private final String label;

    CouponType(Integer value, String label){
        this.value = value;
        this.label = label;
    }
    public Integer getValue() { return value; }
    public String getLabel() { return label;}

}
