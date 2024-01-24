package com.nttdatacasefirst.stockAPI.entity;

import com.nttdatacasefirst.stockAPI.entity.enums.YatirimciTipi;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Hissedar
{
    @Id
    private String sicilNo;
    private String unvan;
    private String adres;
    private String telefon;
    private YatirimciTipi yatirimciTipi;
    @OneToMany(mappedBy = "hissedar")
    private List<HisseSenedi> hisseSenediList;
}
