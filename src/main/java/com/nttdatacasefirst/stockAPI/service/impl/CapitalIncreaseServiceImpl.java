package com.nttdatacasefirst.stockAPI.service.impl;

import com.nttdatacasefirst.stockAPI.dtos.CapitalIncreaseAddModel;
import com.nttdatacasefirst.stockAPI.dtos.CapitalIncreaseGetModel;
import com.nttdatacasefirst.stockAPI.entity.CapitalIncrease;
import com.nttdatacasefirst.stockAPI.exceptions.CapitalIncreaseNotFoundException;
import com.nttdatacasefirst.stockAPI.mapper.MapperCapitalIncrease;
import com.nttdatacasefirst.stockAPI.repository.CapitalIncreaseRepository;
import com.nttdatacasefirst.stockAPI.service.CapitalIncreaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Year;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CapitalIncreaseServiceImpl implements CapitalIncreaseService {
    private final CapitalIncreaseRepository repositoryCapitalIncrease;
    private final MapperCapitalIncrease mapperCapitalIncrease;

    public CapitalIncreaseServiceImpl(@Autowired CapitalIncreaseRepository repositoryCapitalIncrease,
                                      @Autowired MapperCapitalIncrease mapperCapitalIncrease) {

        this.repositoryCapitalIncrease = repositoryCapitalIncrease;
        this.mapperCapitalIncrease = mapperCapitalIncrease;
    }


    @Override
    public CapitalIncreaseGetModel addCapitalIncrease(CapitalIncreaseAddModel addModel) {

        CapitalIncrease createdCapital = mapperCapitalIncrease.toAdd(addModel);

        Optional<CapitalIncrease> lastCapital = repositoryCapitalIncrease.findAll().stream()
                .max(Comparator.comparing(CapitalIncrease::getCapitalValue));
                //.sorted(Comparator.comparing(CapitalIncrease::getCapitalValue).reversed()).findFirst();

        BigDecimal capitalValue = addModel.getRightIssue().add(addModel.getBonusIssue())
                                                            .add(lastCapital.get().getCapitalValue());

        createdCapital.setResidualValue(new BigDecimal(0));
        createdCapital.setCapitalValue(capitalValue);
        createdCapital.setYear(Year.now().getValue());


        repositoryCapitalIncrease.save(createdCapital);

        //Problem, maplenen database'den gelmiyor. O yuzden arrangement'i olmayacak.

        return mapperCapitalIncrease.toModelGet(createdCapital);
    }

    @Override
    public List<CapitalIncreaseGetModel> getAllCapitalIncrease() {

        List<CapitalIncrease> getList = repositoryCapitalIncrease.findAll();
        if(getList.isEmpty())
            throw new CapitalIncreaseNotFoundException("Capital Increase not Found");

        return mapperCapitalIncrease.toModelGetList(getList);
    }

    @Override
    public Optional<CapitalIncrease> findCapitalIncrease(Long arrNo) {

        return repositoryCapitalIncrease.findById(arrNo);
    }


}
