package com.nttdatacasefirst.stockAPI.mapper;

import com.nttdatacasefirst.stockAPI.dtos.CapitalIncreaseAddModel;
import com.nttdatacasefirst.stockAPI.dtos.CapitalIncreaseGetModel;
import com.nttdatacasefirst.stockAPI.entity.CapitalIncrease;

import java.util.List;

public interface MapperCapitalIncrease {
    CapitalIncrease toAdd(CapitalIncreaseAddModel addModel);

    //update model eklenecek

    CapitalIncreaseGetModel toModelGet(CapitalIncrease capitalIncrease);
    List<CapitalIncreaseGetModel> toModelGetList(List<CapitalIncrease> capitalIncreaseList);
}