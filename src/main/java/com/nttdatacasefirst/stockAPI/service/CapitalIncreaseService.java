package com.nttdatacasefirst.stockAPI.service;

import com.nttdatacasefirst.stockAPI.dtos.CapitalIncreaseAddModel;
import com.nttdatacasefirst.stockAPI.dtos.CapitalIncreaseGetModel;
import com.nttdatacasefirst.stockAPI.entity.CapitalIncrease;

import java.util.List;
import java.util.Optional;

public interface CapitalIncreaseService {

    CapitalIncreaseGetModel addCapitalIncrease(CapitalIncreaseAddModel addModel);
    List<CapitalIncreaseGetModel> getAllCapitalIncrease();

    CapitalIncreaseGetModel getCapitalIncreaseByArrNo(String arrNo);

    Optional<CapitalIncrease> findCapitalIncrease(Long arrNo);
}
