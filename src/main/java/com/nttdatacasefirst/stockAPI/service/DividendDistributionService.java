package com.nttdatacasefirst.stockAPI.service;

import com.nttdatacasefirst.stockAPI.entity.DividendDistribution;

public interface DividendDistributionService {

    DividendDistribution getDividendDistributionByCapitalIncrementArrNo(String arrNo);
}
