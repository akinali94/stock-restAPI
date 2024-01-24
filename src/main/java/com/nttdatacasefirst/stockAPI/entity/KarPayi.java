package com.nttdatacasefirst.stockAPI.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class KarPayi {

    @Id
    private int kuponNo;
    private int yilDegeri; //2016-2017-2018-2019-2020...-2025
    private int dagitimOrani;
    private boolean isUsed;
    @ManyToOne
    private HisseSenedi hisseKarPayi;

    //Constructor - Getter - Setter - ToString
    protected KarPayi() {

    }

    public KarPayi(int kuponNo, int yilDegeri, int dagitimOrani, boolean isUsed) {
        this.kuponNo = kuponNo;
        this.yilDegeri = yilDegeri;
        this.dagitimOrani = dagitimOrani;
        this.isUsed = isUsed;
    }

    public int getKuponNo() {
        return kuponNo;
    }

    public void setKuponNo(int kuponNo) {
        this.kuponNo = kuponNo;
    }

    public int getYilDegeri() {
        return yilDegeri;
    }

    public void setYilDegeri(int yilDegeri) {
        this.yilDegeri = yilDegeri;
    }

    public int getDagitimOrani() {
        return dagitimOrani;
    }

    public void setDagitimOrani(int dagitimOrani) {
        this.dagitimOrani = dagitimOrani;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public HisseSenedi getHisseKarPayi() {
        return hisseKarPayi;
    }

    public void setHisseKarPayi(HisseSenedi hisseKarPayi) {
        this.hisseKarPayi = hisseKarPayi;
    }

    @Override
    public String toString() {
        return "KarPayi{" +
                "kuponNo=" + kuponNo +
                ", yilDegeri=" + yilDegeri +
                ", dagitimOrani=" + dagitimOrani +
                ", isUsed=" + isUsed +
                ", hisseKarPayi=" + hisseKarPayi +
                '}';
    }
}
