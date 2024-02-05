package com.nttdatacasefirst.stockAPI.mapper.impl;

import com.nttdatacasefirst.stockAPI.dtos.DividendDistributionGetModel;
import com.nttdatacasefirst.stockAPI.entity.DividendDistribution;
import com.nttdatacasefirst.stockAPI.mapper.MapperDividendDistribution;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperDividendDistributionImpl implements MapperDividendDistribution {
    @Override
    public DividendDistributionGetModel toModelGet(DividendDistribution entity) {
        DividendDistributionGetModel newDiv = new DividendDistributionGetModel();

        newDiv.setDividendYear(entity.getDividendYear());
        newDiv.setDividendRate(entity.getDividentRate());
        newDiv.setSerialNo(entity.getSerialNo());
        newDiv.setCapitalIncreaseArrNo(entity.getCapitalIncrease().getArrangementNo());

        return newDiv;
    }

    @Override
    public List<DividendDistributionGetModel> toModelGetList(List<DividendDistribution> entityList) {
        return entityList.stream().map(x -> new DividendDistributionGetModel(
                    x.getCapitalIncrease().getArrangementNo(),
                    x.getDividendYear(),
                    x.getSerialNo(),
                    x.getDividentRate()))
                .collect(Collectors.toList());
    }
}
