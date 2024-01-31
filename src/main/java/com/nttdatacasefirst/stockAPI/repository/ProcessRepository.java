package com.nttdatacasefirst.stockAPI.repository;

import com.nttdatacasefirst.stockAPI.entity.Coupon;
import com.nttdatacasefirst.stockAPI.entity.Process;
import com.nttdatacasefirst.stockAPI.entity.enums.ProcessType;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProcessRepository extends BaseRepository<Process>{
    List<Process> findByCapitalIncrease_ArrangementNo(Long arrNo);
    List<Process> findByProcessType(ProcessType type);
    List<Process> findByDate(Date date);

    List<Process> findByDividendTotalAfter(int total);
    List<Process> findByDividendTotalBefore(int total);
    List<Process> findByDividendYearAfter(int year);
    List<Process> findByDividendYearBefore(int year);

}
