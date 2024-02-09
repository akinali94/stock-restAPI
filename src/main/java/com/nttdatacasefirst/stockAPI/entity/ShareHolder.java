package com.nttdatacasefirst.stockAPI.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
/*@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "registorNo")*/
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
    @Column(nullable = true)
    @JsonBackReference
    private List<Stock> stockList;
    @OneToMany(mappedBy = "shareHolder")
    @JsonBackReference
    private List<Operation> operationList;

}
