package com.nttdatacasefirst.stockAPI.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/*@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "Id")*/
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private CapitalIncrease capitalIncrease;

    private int serialNo; //seri no

    @Digits(integer = 15, fraction = 2)
    private BigDecimal nominalValue; //nominal deger

    @OneToMany(mappedBy ="stockNo")
    @JsonBackReference
    private List<Coupon> couponList;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(nullable = true)
    @JsonManagedReference
    private ShareHolder shareHolder;

}
