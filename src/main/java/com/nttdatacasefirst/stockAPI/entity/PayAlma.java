package com.nttdatacasefirst.stockAPI.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class PayAlma {
    @Id
    private int kupurNo; //1-16 arasi
    @ManyToOne
    private HisseSenedi hissePayAlma;
    private boolean isUsed;

    //Constructor - Getter - Setter - ToString

    protected PayAlma() {

    }

    public PayAlma(int kupurNo, boolean isUsed) {
        this.kupurNo = kupurNo;
        this.isUsed = isUsed;
    }

    public int getKupurNo() {
        return kupurNo;
    }

    public void setKupurNo(int kupurNo) {
        this.kupurNo = kupurNo;
    }

    public HisseSenedi getHissePayAlma() {
        return hissePayAlma;
    }

    public void setHissePayAlma(HisseSenedi hissePayAlma) {
        this.hissePayAlma = hissePayAlma;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    @Override
    public String toString() {
        return "PayAlma{" +
                "kupurNo=" + kupurNo +
                ", hissePayAlma=" + hissePayAlma +
                ", isUsed=" + isUsed +
                '}';
    }
}
