package com.nttdatacasefirst.stockAPI.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
/*@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "Id")*/
public class DividendDistribution { //Kar payi dagitimi

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private int dividendYear;
    private int serialNo;
    private int dividentRate; //kar payi dagitim orani/ Baslangicta belirlenir
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private CapitalIncrease capitalIncrease;
    @OneToMany(mappedBy = "dividendDistribution")
    @JsonBackReference
    private List<Operation> operationList;
}
