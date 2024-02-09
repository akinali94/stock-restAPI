package com.nttdatacasefirst.stockAPI.service.impl;


import com.nttdatacasefirst.stockAPI.dtos.DividendDistributionAddModel;
import com.nttdatacasefirst.stockAPI.dtos.DividendDistributionGetModel;
import com.nttdatacasefirst.stockAPI.entity.CapitalIncrease;
import com.nttdatacasefirst.stockAPI.entity.DividendDistribution;
import com.nttdatacasefirst.stockAPI.exceptions.CapitalIncreaseNotFoundException;
import com.nttdatacasefirst.stockAPI.exceptions.DividendDistributionNotFoundException;
import com.nttdatacasefirst.stockAPI.mapper.MapperDividendDistribution;
import com.nttdatacasefirst.stockAPI.repository.DividendDistributionRepository;
import com.nttdatacasefirst.stockAPI.service.CapitalIncreaseService;
import com.nttdatacasefirst.stockAPI.service.DividendDistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class DividendDistributionServiceImpl implements DividendDistributionService {

    private final DividendDistributionRepository repositoryDividendDistribution;
    private final MapperDividendDistribution mapperDividendDistribution;
    private final CapitalIncreaseService serviceCapitalIncrease;



    public DividendDistributionServiceImpl(@Autowired DividendDistributionRepository repositoryDividendDistribution,
                                           @Autowired MapperDividendDistribution mapperDividendDistribution,
                                           @Autowired CapitalIncreaseService serviceCapitalIncrease) {
        this.repositoryDividendDistribution = repositoryDividendDistribution;
        this.mapperDividendDistribution = mapperDividendDistribution;
        this.serviceCapitalIncrease = serviceCapitalIncrease;
    }


    @Override
    public List<DividendDistribution> getDividendDistributionByCapitalIncrementArrNo(String arrNo) {
        List<DividendDistribution> getDiv  = repositoryDividendDistribution
                .findByCapitalIncrease_ArrangementNo(Long.parseLong(arrNo));

        if(getDiv.isEmpty())
            throw new DividendDistributionNotFoundException("Dividend Distribution Not Found");

        return getDiv;
    }

    @Override
    public DividendDistributionGetModel addDividendDistribution(DividendDistributionAddModel addModel) {
        //Check Capital Increase
        CapitalIncrease getCapitalIncrease = serviceCapitalIncrease
                .findCapitalIncrease(addModel.getCapitalIncreaseArrNo())
                .orElseThrow(() -> new CapitalIncreaseNotFoundException("Capital Increase Not Found"));

        //Instantiate new Div and Assign values from addModel
        DividendDistribution newDiv = new DividendDistribution();

        newDiv.setCapitalIncrease(getCapitalIncrease);
        newDiv.setDividendYear(addModel.getDividendYear());
        newDiv.setDividentRate(addModel.getDividendRate());

        //Set Serial No
        List<DividendDistribution> getDividendList = repositoryDividendDistribution
                .findByCapitalIncrease_ArrangementNo(getCapitalIncrease.getArrangementNo());
        if(getDividendList.isEmpty()){
            newDiv.setSerialNo(1);
        } else{
            int lastSerialNo = getDividendList.stream()
                    .filter(x -> x.getDividendYear() == getCapitalIncrease.getYear())
                    .max(Comparator.comparing(DividendDistribution::getSerialNo))
                    .orElseThrow(() ->
                            new DividendDistributionNotFoundException("Dividend Dist Not Found when search Serial No"))
                    .getSerialNo();

            newDiv.setSerialNo(lastSerialNo + 1);
        }

        DividendDistribution createdEntity = repositoryDividendDistribution.save(newDiv);

        return mapperDividendDistribution.toModelGet(createdEntity);
    }

    @Override
    public List<DividendDistributionGetModel> getAllDividendDistribution() {
        List<DividendDistribution> getAll = repositoryDividendDistribution.findAll();
        if(getAll.isEmpty())
            throw new DividendDistributionNotFoundException("There is no Dividend Distribution");
        return mapperDividendDistribution.toModelGetList(getAll);
    }

    public List<DividendDistributionGetModel> getDividendDistrubitonByCapitalIncrease(Long arrNo){
        List<DividendDistribution> getDivByCapital = repositoryDividendDistribution
                .findByCapitalIncrease_ArrangementNo(arrNo);
        if(getDivByCapital.isEmpty())
            throw new DividendDistributionNotFoundException("Dividend Dist Not Found When Search by Capital Increase");

        return mapperDividendDistribution.toModelGetList(getDivByCapital);
    }
}












