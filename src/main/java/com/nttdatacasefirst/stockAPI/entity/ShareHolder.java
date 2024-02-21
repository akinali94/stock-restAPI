package com.nttdatacasefirst.stockAPI.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.nttdatacasefirst.stockAPI.entity.enums.InvestorType;
import com.nttdatacasefirst.stockAPI.entity.enums.converters.InvestorTypeConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;
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
/*@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "registorNo")*/
public class ShareHolder {
    @Id
    @Column(name ="user_id")
    private Long registorNo; //sicilNo

    private String title; //unvan

    private String address;

    @Pattern(regexp = "[0-9\\s]{12}")
    private String phoneNo;

    @Digits(integer = 15, fraction = 2)
    private BigDecimal amountMoney;
    @Digits(integer = 15, fraction = 2)
    private BigDecimal requestMoney;

    @Convert(converter = InvestorTypeConverter.class)
    private InvestorType investorType;

    @OneToMany(mappedBy = "shareHolder")
    @Column(nullable = true)
    @JsonBackReference
    private List<Stock> stockList;

    @OneToMany(mappedBy = "shareHolder")
    @JsonBackReference
    private List<Operation> operationList;

    @OneToOne
    @MapsId
    @JoinColumn(name = "register_no")
    private User user;

}
