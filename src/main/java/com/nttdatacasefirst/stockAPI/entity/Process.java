package com.nttdatacasefirst.stockAPI.entity;

import com.nttdatacasefirst.stockAPI.entity.enums.ProcessType;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Process {
    @Id
    private int Id;
    private ProcessType processType;
    @OneToOne
    private Coupon coupon;
    private Date date;

    private int dividendYear;
    private int dividendTotal; //kar payi orani X nominal

}
