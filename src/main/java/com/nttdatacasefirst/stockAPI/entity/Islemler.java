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

    //Constructor - Getter - Setter - ToString

    protected Islemler() {

    }

    public Islemler(int islemId, IslemTipi islemTipi, HisseSenedi hisseSenedi, Date saat, int karPayiDonemi, int karPayiTutari) {
        this.islemId = islemId;
        this.islemTipi = islemTipi;
        this.hisseSenedi = hisseSenedi;
        this.saat = saat;
        this.karPayiDonemi = karPayiDonemi;
        this.karPayiTutari = karPayiTutari;
    }

    public int getIslemId() {
        return islemId;
    }

    public void setIslemId(int islemId) {
        this.islemId = islemId;
    }

    public IslemTipi getIslemTipi() {
        return islemTipi;
    }

    public void setIslemTipi(IslemTipi islemTipi) {
        this.islemTipi = islemTipi;
    }

    public HisseSenedi getHisseSenedi() {
        return hisseSenedi;
    }

    public void setHisseSenedi(HisseSenedi hisseSenedi) {
        this.hisseSenedi = hisseSenedi;
    }

    public Date getSaat() {
        return saat;
    }

    public void setSaat(Date saat) {
        this.saat = saat;
    }

    public int getKarPayiDonemi() {
        return karPayiDonemi;
    }

    public void setKarPayiDonemi(int karPayiDonemi) {
        this.karPayiDonemi = karPayiDonemi;
    }

    public int getKarPayiTutari() {
        return karPayiTutari;
    }

    public void setKarPayiTutari(int karPayiTutari) {
        this.karPayiTutari = karPayiTutari;
    }

    @Override
    public String toString() {
        return "Islemler{" +
                "islemId=" + islemId +
                ", islemTipi=" + islemTipi +
                ", hisseSenedi=" + hisseSenedi +
                ", saat=" + saat +
                ", karPayiDonemi=" + karPayiDonemi +
                ", karPayiTutari=" + karPayiTutari +
                '}';
    }
}
