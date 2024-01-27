package com.nttdatacasefirst.stockAPI.entity;

import com.nttdatacasefirst.stockAPI.entity.enums.InvestorType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class ShareHolder {
    @Id
    @Column(length = 8, unique = true)
    private String registorNo; //sicilNo
    private String title; //unvan
    private String address;
    private String phoneNo;
    private InvestorType investorType;
    @OneToMany(mappedBy = "shareHolder")
    private List<Stock> stockList;

}
