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
}
