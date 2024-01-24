package com.nttdatacasefirst.stockAPI.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class HisseSenedi {
    @Id
    @GeneratedValue
    @Column(length = 8)
    private int seriNo;

    @Column(precision = 10, scale = 2)
    private double nominalDeger;

    @ManyToOne
    private SermayeArtisi sermayeArtisi;
    @ManyToOne
    @Column(nullable = true)
    private Hissedar hissedar;

    @OneToMany(mappedBy = "hisseKarPayi")
    private List<PayAlma> payAlmaList;
    @OneToMany(mappedBy = "hissePayAlma")
    private List<KarPayi> karPayiList;

}
