package com.nttdatacasefirst.stockAPI.repository;

import com.nttdatacasefirst.stockAPI.entity.DividendDistribution;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DividendDistributionRepository extends BaseRepository<DividendDistribution> {
    List<DividendDistribution> findByCapitalIncrease_ArrangementNo(Long arrNo);

}
