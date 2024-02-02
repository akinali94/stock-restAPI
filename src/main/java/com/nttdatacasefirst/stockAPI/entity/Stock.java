package com.nttdatacasefirst.stockAPI.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
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

    @Digits(integer = 15, fraction = 2)
    private BigDecimal nominalValue; //nominal deger

    @OneToMany(mappedBy ="stockNo")
    private List<Coupon> couponList;

    @ManyToOne(fetch = FetchType.LAZY)
    private ShareHolder shareHolder;

}
