package com.nttdatacasefirst.stockAPI.entity;

import com.nttdatacasefirst.stockAPI.entity.enums.YatirimciTipi;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Hissedar
{
    @Id
    @Column(length = 8, unique = true)
    private String sicilNo;
    private String unvan;
    private String adres;
    private String telefon;
    private YatirimciTipi yatirimciTipi;
    @OneToMany(mappedBy = "hissedar")
    private List<HisseSenedi> hisseSenediList;

    //Constructor - Getter - Setter - ToString
    protected Hissedar(){

    }

    public Hissedar(String sicilNo, String unvan, String adres, String telefon, YatirimciTipi yatirimciTipi) {
        this.sicilNo = sicilNo;
        this.unvan = unvan;
        this.adres = adres;
        this.telefon = telefon;
        this.yatirimciTipi = yatirimciTipi;
    }

    public String getSicilNo() {
        return sicilNo;
    }

    public void setSicilNo(String sicilNo) {
        this.sicilNo = sicilNo;
    }

    public String getUnvan() {
        return unvan;
    }

    public void setUnvan(String unvan) {
        this.unvan = unvan;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public YatirimciTipi getYatirimciTipi() {
        return yatirimciTipi;
    }

    public void setYatirimciTipi(YatirimciTipi yatirimciTipi) {
        this.yatirimciTipi = yatirimciTipi;
    }

    public List<HisseSenedi> getHisseSenediList() {
        return hisseSenediList;
    }

    public void setHisseSenediList(List<HisseSenedi> hisseSenediList) {
        this.hisseSenediList = hisseSenediList;
    }

    @Override
    public String toString() {
        return "Hissedar{" +
                "sicilNo='" + sicilNo + '\'' +
                ", unvan='" + unvan + '\'' +
                ", adres='" + adres + '\'' +
                ", telefon='" + telefon + '\'' +
                ", yatirimciTipi=" + yatirimciTipi +
                ", hisseSenediList=" + hisseSenediList +
                '}';
    }
}
