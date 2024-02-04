package com.nttdatacasefirst.stockAPI.repository;

import com.nttdatacasefirst.stockAPI.entity.Operation;
import com.nttdatacasefirst.stockAPI.entity.enums.OperationType;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OperationsRepository extends BaseRepository<Operation>{
    List<Operation> findByCapitalIncrease_ArrangementNo(Long arrNo);
    List<Operation> findByProcessType(OperationType type);
    List<Operation> findByDate(Date date);

    List<Operation> findByDividendTotalAfter(int total);
    List<Operation> findByDividendTotalBefore(int total);
    List<Operation> findByDividendYearAfter(int year);
    List<Operation> findByDividendYearBefore(int year);

}
