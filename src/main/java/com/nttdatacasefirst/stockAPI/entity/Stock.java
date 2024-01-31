package com.nttdatacasefirst.stockAPI.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    @Id
    @GeneratedValue
    private Long Id;
    @ManyToOne(fetch = FetchType.LAZY)
    private CapitalIncrease capitalIncrease;
    private int serialNo; //seri no

    //@Column(precision = 10, scale = 2)
    @NumberFormat(pattern = "#.##")
    private double nominalValue; //nominal deger

    @OneToMany(mappedBy ="stockNo")
    private List<Coupon> couponList;

    @ManyToOne(fetch = FetchType.LAZY)
    private ShareHolder shareHolder;

}
