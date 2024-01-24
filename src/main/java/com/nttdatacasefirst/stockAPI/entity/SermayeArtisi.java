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
    private int sermayeDegeri; //Eski sermaye degeri + bedelli + bedelsiz
    @OneToMany(mappedBy = "sermayeArtisi")
    private List<HisseSenedi> hisseSenetleri;

    //Constructor - Getter - Setter - ToString
    protected SermayeArtisi(){

    }

    public SermayeArtisi(int tertipNo, int yil, int bedelliMiktar, int bedelsizMiktar, byte artirimOrani, int sermayeDegeri) {
        this.tertipNo = tertipNo;
        this.yil = yil;
        this.bedelliMiktar = bedelliMiktar;
        this.bedelsizMiktar = bedelsizMiktar;
        this.artirimOrani = artirimOrani;
        this.sermayeDegeri = sermayeDegeri;
    }

    public int getTertipNo() {
        return tertipNo;
    }

    public void setTertipNo(int tertipNo) {
        this.tertipNo = tertipNo;
    }

    public int getYil() {
        return yil;
    }

    public void setYil(int yil) {
        this.yil = yil;
    }

    public int getBedelliMiktar() {
        return bedelliMiktar;
    }

    public void setBedelliMiktar(int bedelliMiktar) {
        this.bedelliMiktar = bedelliMiktar;
    }

    public int getBedelsizMiktar() {
        return bedelsizMiktar;
    }

    public void setBedelsizMiktar(int bedelsizMiktar) {
        this.bedelsizMiktar = bedelsizMiktar;
    }

    public byte getArtirimOrani() {
        return artirimOrani;
    }

    public void setArtirimOrani(byte artirimOrani) {
        this.artirimOrani = artirimOrani;
    }

    public int getSermayeDegeri() {
        return sermayeDegeri;
    }

    public void setSermayeDegeri(int sermayeDegeri) {
        this.sermayeDegeri = sermayeDegeri;
    }

    public List<HisseSenedi> getHisseSenetleri() {
        return hisseSenetleri;
    }

    public void setHisseSenetleri(List<HisseSenedi> hisseSenetleri) {
        this.hisseSenetleri = hisseSenetleri;
    }

    @Override
    public String toString() {
        return "SermayeArtisi{" +
                "tertipNo=" + tertipNo +
                ", yil=" + yil +
                ", bedelliMiktar=" + bedelliMiktar +
                ", bedelsizMiktar=" + bedelsizMiktar +
                ", artirimOrani=" + artirimOrani +
                ", sermayeDegeri=" + sermayeDegeri +
                ", hisseSenetleri=" + hisseSenetleri +
                '}';
    }
}
