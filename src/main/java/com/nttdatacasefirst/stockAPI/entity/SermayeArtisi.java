package com.nttdatacasefirst.stockAPI.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class SermayeArtisi {
    @Id
    @GeneratedValue
    private int tertipNo;
    private int yil;
    private int bedelliMiktar;
    private int bedelsizMiktar;
    private byte artirimOrani;
    private int eskiSermayeDegeri;
    private int sermayeDegeri; //Eski sermaye degeri + bedelli + bedelsiz

    @OneToMany(mappedBy = "sermayeArtisi")
    private List<HisseSenedi> hisseSenetleri;
}

/*
    public SermayeArtisi(int bedelliMiktar, int bedelsizMiktar, int artirimOrani){
        this.bedelliMiktar = bedelliMiktar;
        this.bedelsizMiktar = bedelsizMiktar;
        this.artirimOrani = artirimOrani;
        this.sermayeDegeri = eskiSermayeDegeri + bedelliMiktar + bedelsizMiktar;

    }
*/