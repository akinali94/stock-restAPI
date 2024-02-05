package com.nttdatacasefirst.stockAPI.mapper;

import com.nttdatacasefirst.stockAPI.dtos.DividendDistributionGetModel;
import com.nttdatacasefirst.stockAPI.entity.DividendDistribution;

import java.util.List;

public interface MapperDividendDistribution {

    DividendDistributionGetModel toModelGet(DividendDistribution entity);

    List<DividendDistributionGetModel> toModelGetList(List<DividendDistribution> entityList);
}
