package com.nttdatacasefirst.stockAPI.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class CapitalIncrease {
    @Id
    @GeneratedValue
    private int arrangementNo; //Tertip numarai
    private int year;
    private int rightIssue; //bedelli sermaye arttirimi
    private int bonusIssue; //bedelsiz sermaye artirimi
    private byte increasingRate; //Artirim orani
    private int capitalValue; //Eski sermaye degeri + bedelli + bedelsiz
    @OneToMany(mappedBy = "capitalIncrease")
    private List<Stock> stockList;

/*    @OneToMany(mappedBy = "arrangementNo")
    private List<Coupon> couponList;*/


}
