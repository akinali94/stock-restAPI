package com.nttdatacasefirst.stockAPI.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class PayAlma {
    @Id
    private int kupurNo; //1-16 arasi
    @ManyToOne
    private HisseSenedi hissePayAlma;
    private boolean isUsed;
}
