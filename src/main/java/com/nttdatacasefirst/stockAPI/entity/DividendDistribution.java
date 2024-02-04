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
@AllArgsConstructor
@NoArgsConstructor
public class DividendDistribution { //Kar payi dagitimi

    @Id
    @GeneratedValue
    private Long Id;
    private int dividendYear;
    private int serialNo;
    private int dividentRate; //kar payi dagitim orani/ Baslangicta belirlenir
    @ManyToOne(fetch = FetchType.LAZY)
    private CapitalIncrease capitalIncrease;
    @OneToMany
    private List<Operation> operationList;
}
