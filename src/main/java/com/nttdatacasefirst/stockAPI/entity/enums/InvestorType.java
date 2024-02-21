package com.nttdatacasefirst.stockAPI.entity.enums;

public enum InvestorType {
    NATURALPERSON(1L, "NATURALPERSON"), //Gercek kisi
    LEGALENTITY(2L, "LEGALENTITY"), //Tuzel kisi
    NOTDETERMINED(3L, "NOTDETERMINED"); //Secilmedi

    private final Long value;
    private final String label;

    InvestorType(Long value, String label){
        this.value = value;
        this.label = label;
    }

    public Long getValue(){ return value; }
    public String getLabel(){ return label; }

}
