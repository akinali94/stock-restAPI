package com.nttdatacasefirst.stockAPI.entity.enums;

public enum ProcessType {
    STOCK(1, "STOCK"), //Hisse Senedi islemleri
    DIVIDEND(2, "DIVIDEND"); //Kar Payi islemleri

    private final Integer value;
    private final String label;
    ProcessType(Integer value, String label){
        this.label = label;
        this.value = value;
    }
    public Integer getValue(){return value; }
    public String getLabel(){ return label; }
}
