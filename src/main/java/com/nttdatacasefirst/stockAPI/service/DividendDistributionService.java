package com.nttdatacasefirst.stockAPI.service;

import com.nttdatacasefirst.stockAPI.dtos.DividendDistributionAddModel;
import com.nttdatacasefirst.stockAPI.dtos.DividendDistributionGetModel;
import com.nttdatacasefirst.stockAPI.entity.DividendDistribution;

import java.util.List;

public interface DividendDistributionService {

    List<DividendDistribution> getDividendDistributionByCapitalIncrementArrNo(String arrNo);

    DividendDistributionGetModel addDividendDistribution(DividendDistributionAddModel addModel);
    List<DividendDistributionGetModel> getAllDividendDistribution();
    List<DividendDistributionGetModel> getDividendDistrubitonByCapitalIncrease(Long arrNo);

}
