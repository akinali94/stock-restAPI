package com.nttdatacasefirst.stockAPI.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private CapitalIncrease capitalIncrease;
    @OneToMany
    @JsonManagedReference
    private List<Operation> operationList;
}
