package com.nttdatacasefirst.stockAPI.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.NumberFormat;

import java.util.List;

@Entity
public class Stock {
    @Id
    @GeneratedValue
    private int Id;
    @ManyToOne
    private CapitalIncrease capitalIncrease;
    private int serialNo; //seri no

    //@Column(precision = 10, scale = 2)
    @NumberFormat(pattern = "#.##")
    private double nominalValue; //nominal deger

    @OneToMany(mappedBy ="stockNo")
    private List<Coupon> couponList;

    @ManyToOne
    @JoinColumn(nullable = true)
    private ShareHolder shareHolder;

}
