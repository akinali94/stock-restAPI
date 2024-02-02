package com.nttdatacasefirst.stockAPI.entity;

import com.nttdatacasefirst.stockAPI.entity.enums.InvestorType;
import com.nttdatacasefirst.stockAPI.entity.enums.converters.InvestorTypeConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
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
    private Long registorNo; //sicilNo
    private String title; //unvan
    private String address;
    @Pattern(regexp = "[0-9\\s]{12}")
    private String phoneNo;
    @Convert(converter = InvestorTypeConverter.class)
    private InvestorType investorType;
    @OneToMany(mappedBy = "shareHolder")
    private List<Stock> stockList;

}
