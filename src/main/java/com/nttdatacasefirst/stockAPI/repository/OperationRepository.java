package com.nttdatacasefirst.stockAPI.repository;

import com.nttdatacasefirst.stockAPI.entity.Operation;
import com.nttdatacasefirst.stockAPI.entity.enums.OperationType;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OperationRepository extends BaseRepository<Operation>{
    List<Operation> findByStockCapitalIncreaseArrangementNo(Long No);
    List<Operation> findByOperationType(OperationType type);
    List<Operation> findByDate(Date date);
    List<Operation> findByDividendTotalAfter(int total);
    List<Operation> findByDividendTotalBefore(int total);
    List<Operation> findByDateBefore(Date date);
    List<Operation> findByDateAfter(Date date);


}