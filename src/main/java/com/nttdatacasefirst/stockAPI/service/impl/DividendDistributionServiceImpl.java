package com.nttdatacasefirst.stockAPI.service.impl;


import com.nttdatacasefirst.stockAPI.entity.DividendDistribution;
import com.nttdatacasefirst.stockAPI.exceptions.DividendDistributionNotFoundException;
import com.nttdatacasefirst.stockAPI.repository.DividendDistributionRepository;
import com.nttdatacasefirst.stockAPI.service.DividendDistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DividendDistributionServiceImpl implements DividendDistributionService {

    private final DividendDistributionRepository repositoryDividendDistribution;


    public DividendDistributionServiceImpl(@Autowired DividendDistributionRepository repositoryDividendDistribution) {
        this.repositoryDividendDistribution = repositoryDividendDistribution;
    }


    @Override
    public DividendDistribution getDividendDistributionByCapitalIncrementArrNo(String arrNo) {
        DividendDistribution getDiv  = repositoryDividendDistribution.findByCapitalIncrease_ArrangementNo(Long.parseLong(arrNo));

        if(getDiv == null)
            throw new DividendDistributionNotFoundException("Dividend Distribution Not Found");

        return getDiv;
    }
}
