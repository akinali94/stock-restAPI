package com.nttdatacasefirst.stockAPI.entity.enums;

public enum Role {
    USER(1L, "USER"),
    ADMIN(2L, "ADMIN");

    private final Long value;
    private final String label;

    Role(Long value, String label){
        this.label = label;
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    public String getLabel(){
        return label;
    }
}
