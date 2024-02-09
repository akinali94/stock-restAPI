package com.nttdatacasefirst.stockAPI.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nttdatacasefirst.stockAPI.entity.enums.OperationType;
import com.nttdatacasefirst.stockAPI.entity.enums.converters.OperationTypeConverter;
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
public class Operation {
    @Id
    @GeneratedValue
    private Long Id;
    @Convert(converter = OperationTypeConverter.class)
    private OperationType operationType;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Stock stock;
    private Date date;
    private int dividentYear;
    private int dividendTotal; //kar payi orani X nominal
    //hangi kar payi donemine ait oldugu
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private DividendDistribution dividendDistribution;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private ShareHolder shareHolder;

}
