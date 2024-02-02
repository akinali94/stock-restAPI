package com.nttdatacasefirst.stockAPI.repository;

import com.nttdatacasefirst.stockAPI.entity.CapitalIncrease;
import com.nttdatacasefirst.stockAPI.entity.Stock;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Repository
public interface StockRepository extends BaseRepository<Stock> {
    Stock findBySerialNo(int seriNo);
    List<Stock> findByNominalValueLessThanOrderByNominalValue(BigDecimal value);
    List<Stock> findByCapitalIncrease(CapitalIncrease capitalIncrease);

    List<Stock> findByShareHolder_RegistorNo(Long regNo);

}
