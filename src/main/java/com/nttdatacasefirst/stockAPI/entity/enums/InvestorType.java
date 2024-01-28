package com.nttdatacasefirst.stockAPI.entity.enums;

public enum InvestorType {
    NATURALPERSON(1, "NATURALPERSON"), //Gercek kisi
    LEGALENTITY(2, "LEGALENTITY"); //Tuzel kisi

    private final Integer value;
    private final String label;

    InvestorType(Integer value, String label){
        this.value = value;
        this.label = label;
    }

    public Integer getValue(){ return value; }
    public String getLabel(){ return label; }

}
