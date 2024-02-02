package com.nttdatacasefirst.stockAPI.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
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
    @Digits(integer = 15, fraction = 2)
    private BigDecimal rightIssue; //bedelli sermaye arttirimi
    @Digits(integer = 15, fraction = 2)
    private BigDecimal bonusIssue; //bedelsiz sermaye artirimi
    private byte increasingRate; //Artirim orani
    @Digits(integer = 15, fraction = 2)
    private BigDecimal capitalValue; //Eski sermaye degeri + bedelli + bedelsiz
    private BigDecimal residualValue; //Hisse senedinde kullanilmasi capitalValue'dan kalan deger.
    @OneToMany(mappedBy = "capitalIncrease")
    private List<Stock> stockList;

/*    @OneToMany(mappedBy = "arrangementNo")
    private List<Coupon> couponList;*/
    @OneToMany(mappedBy = "capitalIncrease")
    private List<Process> processList;
}
