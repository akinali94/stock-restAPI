package com.nttdatacasefirst.stockAPI.entity;

import com.nttdatacasefirst.stockAPI.entity.enums.IslemTipi;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Islemler {
    @Id
    private int islemId;
    private IslemTipi islemTipi;
    @OneToOne
    private HisseSenedi hisseSenedi;
    private Date saat;
    @Column(nullable = true)
    private int karPayiDonemi;
    private int karPayiTutari;
}
