package com.nttdatacasefirst.stockAPI.repository;

import com.nttdatacasefirst.stockAPI.entity.CapitalIncrease;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CapitalIncreaseRepository extends BaseRepository<CapitalIncrease>  {
    CapitalIncrease findByArrangementNo(Long arrNo);
    CapitalIncrease findByYear(int year);
    List<CapitalIncrease> findByCapitalValueLessThan(BigDecimal value);
}
