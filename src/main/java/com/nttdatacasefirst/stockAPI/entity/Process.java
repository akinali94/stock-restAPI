package com.nttdatacasefirst.stockAPI.entity;

import com.nttdatacasefirst.stockAPI.entity.enums.ProcessType;
import com.nttdatacasefirst.stockAPI.entity.enums.converters.ProcessTypeConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Process {
    @Id
    @GeneratedValue
    private Long Id;
    @Convert(converter = ProcessTypeConverter.class)
    private ProcessType processType;
    @ManyToOne(fetch = FetchType.LAZY)
    private CapitalIncrease capitalIncrease;
    @ManyToOne(fetch = FetchType.LAZY)
    private Coupon coupon;
    private Date date;
    private int dividentRate; //kar payi dagitim orani/ Baslangicta belirlenir
    private int dividendYear;
    private int dividendTotal; //kar payi orani X nominal

}
