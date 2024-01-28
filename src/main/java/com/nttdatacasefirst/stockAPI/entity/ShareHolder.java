package com.nttdatacasefirst.stockAPI.entity;

import com.nttdatacasefirst.stockAPI.entity.enums.InvestorType;
import com.nttdatacasefirst.stockAPI.entity.enums.converters.InvestorTypeConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShareHolder {
    @Id
    @Column(length = 8, unique = true)
    private String registorNo; //sicilNo
    private String title; //unvan
    private String address;
    private String phoneNo;
    @Convert(converter = InvestorTypeConverter.class)
    private InvestorType investorType;
    @OneToMany(mappedBy = "shareHolder")
    private List<Stock> stockList;

}
