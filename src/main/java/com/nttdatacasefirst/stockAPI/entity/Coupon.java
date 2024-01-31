package com.nttdatacasefirst.stockAPI.entity;

import com.nttdatacasefirst.stockAPI.entity.enums.CouponType;
import com.nttdatacasefirst.stockAPI.entity.enums.converters.CouponTypeConverter;
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
public class Coupon {

    @Id
    @GeneratedValue
    private Long Id;
/*    @ManyToOne
    private CapitalIncrease arrangementNo;*/
    @Convert(converter = CouponTypeConverter.class)
    private CouponType type;
    private int arrangementNo; //Sermaye artisindan gelecek.
    private int clippingNo; //kupur numarasi - pay alma
    private int yearNo; //kar payi kuponu
    private boolean used;
    @ManyToOne(fetch = FetchType.LAZY)
    private Stock stockNo;
    @OneToMany(mappedBy = "coupon")
    private List<Process> processList;

}
