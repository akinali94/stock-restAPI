package com.nttdatacasefirst.stockAPI.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class HisseSenedi {
    @Id
    @GeneratedValue
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

    //Constructor - Getter - Setter - ToString
    protected HisseSenedi(){

    };

    public HisseSenedi(int seriNo, double nominalDeger) {
        this.seriNo = seriNo;
        this.nominalDeger = nominalDeger;
    }

    public int getSeriNo() {
        return seriNo;
    }

    public void setSeriNo(int seriNo) {
        this.seriNo = seriNo;
    }

    public double getNominalDeger() {
        return nominalDeger;
    }

    public void setNominalDeger(double nominalDeger) {
        this.nominalDeger = nominalDeger;
    }

    public SermayeArtisi getSermayeArtisi() {
        return sermayeArtisi;
    }

    public void setSermayeArtisi(SermayeArtisi sermayeArtisi) {
        this.sermayeArtisi = sermayeArtisi;
    }

    public Hissedar getHissedar() {
        return hissedar;
    }

    public void setHissedar(Hissedar hissedar) {
        this.hissedar = hissedar;
    }

    public List<PayAlma> getPayAlmaList() {
        return payAlmaList;
    }

    public void setPayAlmaList(List<PayAlma> payAlmaList) {
        this.payAlmaList = payAlmaList;
    }

    public List<KarPayi> getKarPayiList() {
        return karPayiList;
    }

    public void setKarPayiList(List<KarPayi> karPayiList) {
        this.karPayiList = karPayiList;
    }

    @Override
    public String toString() {
        return "HisseSenedi{" +
                "seriNo=" + seriNo +
                ", nominalDeger=" + nominalDeger +
                ", sermayeArtisi=" + sermayeArtisi +
                ", hissedar=" + hissedar +
                ", payAlmaList=" + payAlmaList +
                ", karPayiList=" + karPayiList +
                '}';
    }
}
