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

        //Mapped Model to Entity
        CapitalIncrease createdCapital = mapperCapitalIncrease.toAdd(addModel);

        //Get Last CapitalIncrease to get capitalValue
        Optional<CapitalIncrease> getLastCapital = repositoryCapitalIncrease.findAll().stream()
                .max(Comparator.comparing(CapitalIncrease::getCapitalValue));

        //Assign Capital Value
        BigDecimal capitalValue = getLastCapital.map(capitalIncrease -> addModel.getRightIssue().add(addModel.getBonusIssue())
                .add(capitalIncrease.getCapitalValue()))
                .orElseGet(() -> addModel.getRightIssue().add(addModel.getBonusIssue()));

        createdCapital.setCapitalValue(capitalValue);

        //Assign residaulValue to control Stock production
        createdCapital.setResidualValue(capitalValue);

        //Assign year of CapitalIncrease
        createdCapital.setYear(Year.now().getValue());

        //Save to repo
        CapitalIncrease saved = repositoryCapitalIncrease.save(createdCapital);

        return mapperCapitalIncrease.toModelGet(saved);
    }

    @Override
    public List<CapitalIncreaseGetModel> getAllCapitalIncrease() {

        List<CapitalIncrease> getList = repositoryCapitalIncrease.findAll();
        if(getList.isEmpty())
            throw new CapitalIncreaseNotFoundException("Capital Increase not Found");

        return mapperCapitalIncrease.toModelGetList(getList);
    }

    @Override
    public CapitalIncreaseGetModel getCapitalIncreaseByArrNo(String arrNo){
        return mapperCapitalIncrease.toModelGet(
                repositoryCapitalIncrease.findById(Long.parseLong(arrNo))
                .orElseThrow(() -> new CapitalIncreaseNotFoundException("Capital Increase with this Arr No Not Found"))
        );
    }

    @Override
    public Optional<CapitalIncrease> findCapitalIncrease(Long arrNo) {

        return repositoryCapitalIncrease.findById(arrNo);
    }


}
