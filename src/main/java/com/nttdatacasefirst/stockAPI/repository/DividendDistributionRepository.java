package com.nttdatacasefirst.stockAPI.repository;

import com.nttdatacasefirst.stockAPI.entity.DividendDistribution;
import org.springframework.stereotype.Repository;

@Repository
public interface DividendDistributionRepository extends BaseRepository<DividendDistribution> {
    DividendDistribution findByCapitalIncrease_ArrangementNo(Long arrNo);

}
