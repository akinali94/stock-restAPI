package com.nttdatacasefirst.stockAPI.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.nttdatacasefirst.stockAPI.entity.enums.OperationType;
import com.nttdatacasefirst.stockAPI.entity.enums.converters.OperationTypeConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/*@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "Id")*/
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Convert(converter = OperationTypeConverter.class)
    private OperationType operationType;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Stock stock;
    private Date date;
    private int dividentYear;
    private BigDecimal dividendTotal; //kar payi orani X nominal
    //hangi kar payi donemine ait oldugu
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private DividendDistribution dividendDistribution;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private ShareHolder shareHolder;

}
