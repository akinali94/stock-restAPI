package com.nttdatacasefirst.stockAPI.entity;

import com.fasterxml.jackson.annotation.*;
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
/*@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "arrangementNo")*/
public class CapitalIncrease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long arrangementNo; //Tertip numarasi
    private int year;
    @Digits(integer = 15, fraction = 2)
    private BigDecimal rightIssue; //bedelli sermaye arttirimi
    @Digits(integer = 15, fraction = 2)
    private BigDecimal bonusIssue; //bedelsiz sermaye artirimi

    private int increasingRate; //Artirim orani
    @Digits(integer = 15, fraction = 2)
    private BigDecimal capitalValue; //Eski sermaye degeri + bedelli + bedelsiz
    @Digits(integer = 15, fraction = 2)
    private BigDecimal residualValue; //Hisse senedinde kullanilmasi capitalValue'dan kalan deger.
    @OneToMany(mappedBy = "capitalIncrease")
    @JsonBackReference
    private List<Stock> stockList;
    @OneToMany(mappedBy = "capitalIncrease")
    @JsonBackReference
    private List<DividendDistribution> dividendDistributionList;

}
