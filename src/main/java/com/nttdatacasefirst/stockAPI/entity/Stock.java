package com.nttdatacasefirst.stockAPI.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CapitalIncrease capitalIncrease;
    private int serialNo; //seri no

    @Digits(integer = 15, fraction = 2)
    private BigDecimal nominalValue; //nominal deger

    @OneToMany(mappedBy ="stockNo")
    @JsonManagedReference
    private List<Coupon> couponList;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(nullable = true)
    private ShareHolder shareHolder;

}
