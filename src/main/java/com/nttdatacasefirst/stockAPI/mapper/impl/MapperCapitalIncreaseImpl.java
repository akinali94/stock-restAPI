package com.nttdatacasefirst.stockAPI.mapper.impl;

import com.nttdatacasefirst.stockAPI.dtos.CapitalIncreaseAddModel;
import com.nttdatacasefirst.stockAPI.dtos.CapitalIncreaseGetModel;
import com.nttdatacasefirst.stockAPI.entity.CapitalIncrease;
import com.nttdatacasefirst.stockAPI.mapper.MapperCapitalIncrease;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperCapitalIncreaseImpl implements MapperCapitalIncrease {
    @Override
    public CapitalIncrease toAdd(CapitalIncreaseAddModel addModel) {
        CapitalIncrease newCapital = new CapitalIncrease();

        newCapital.setIncreasingRate(addModel.getIncreasingRate());
        newCapital.setRightIssue(addModel.getRightIssue());
        newCapital.setBonusIssue(addModel.getBonusIssue());

        return newCapital;
    }

    @Override
    public CapitalIncreaseGetModel toModelGet(CapitalIncrease capitalIncrease) {
        CapitalIncreaseGetModel getCapital = new CapitalIncreaseGetModel();

        getCapital.setArrangementNo(capitalIncrease.getArrangementNo());
        getCapital.setIncreasingRate(capitalIncrease.getIncreasingRate());
        getCapital.setCapitalValue(capitalIncrease.getCapitalValue());
        getCapital.setRightIssue(capitalIncrease.getRightIssue());
        getCapital.setBonusIssue(capitalIncrease.getBonusIssue());
        getCapital.setYear(capitalIncrease.getYear());

        return getCapital;
    }

    @Override
    public List<CapitalIncreaseGetModel> toModelGetList(List<CapitalIncrease> capitalIncreaseList) {

        return capitalIncreaseList.stream()
                .map(x -> new CapitalIncreaseGetModel(
                    x.getArrangementNo(),
                    x.getYear(),
                    x.getRightIssue(),
                    x.getBonusIssue(),
                    x.getIncreasingRate(),
                    x.getCapitalValue()))
                .collect(Collectors.toList());
    }
}
