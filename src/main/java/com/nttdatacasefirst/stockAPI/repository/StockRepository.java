package com.nttdatacasefirst.stockAPI.repository;

import com.nttdatacasefirst.stockAPI.entity.CapitalIncrease;
import com.nttdatacasefirst.stockAPI.entity.Stock;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface StockRepository extends BaseRepository<Stock> {
    Stock findBySerialNo(int seriNo);
    List<Stock> findByNominalValueLessThanOrderByNominalValue(double value);
    List<Stock> findByCapitalIncreaseArrangementNoIn(Collection<Long> arrNo);
    List<Stock> findByShareHolder_RegistorNo(Long regNo);

}
