package com.nttdatacasefirst.stockAPI.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
/*@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "Id")*/
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Convert(converter = CouponTypeConverter.class)
    private CouponType type;
    private int couponNo;
    private Long arrangementNo; //Sermaye artisindan gelecek.
    private int clippingNo; //kupur numarasi - pay alma
    private int yearNo; //kar payi kuponu
    private boolean used;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Stock stockNo;

}
