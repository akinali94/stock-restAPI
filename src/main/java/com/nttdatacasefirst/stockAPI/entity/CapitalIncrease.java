package com.nttdatacasefirst.stockAPI.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CapitalIncrease {
    @Id
    @GeneratedValue
    private Long arrangementNo; //Tertip numarasi
    private int year;
    private int rightIssue; //bedelli sermaye arttirimi
    private int bonusIssue; //bedelsiz sermaye artirimi
    private byte increasingRate; //Artirim orani
    private int capitalValue; //Eski sermaye degeri + bedelli + bedelsiz
    @OneToMany(mappedBy = "capitalIncrease")
    private List<Stock> stockList;

/*    @OneToMany(mappedBy = "arrangementNo")
    private List<Coupon> couponList;*/
    @OneToMany(mappedBy = "capitalIncrease")
    private List<Process> processList;
}
