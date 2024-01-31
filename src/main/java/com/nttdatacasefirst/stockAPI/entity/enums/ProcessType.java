package com.nttdatacasefirst.stockAPI.entity.enums;

public enum ProcessType {
    STOCK(1L, "STOCK"), //Hisse Senedi islemleri
    DIVIDEND(2L, "DIVIDEND"); //Kar Payi islemleri

    private final Long value;
    private final String label;
    ProcessType(Long value, String label){
        this.label = label;
        this.value = value;
    }
    public Long getValue(){return value; }
    public String getLabel(){ return label; }
}
