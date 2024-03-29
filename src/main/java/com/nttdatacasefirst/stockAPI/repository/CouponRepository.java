package com.nttdatacasefirst.stockAPI.repository;

import com.nttdatacasefirst.stockAPI.entity.Coupon;
import com.nttdatacasefirst.stockAPI.entity.Stock;
import com.nttdatacasefirst.stockAPI.entity.enums.CouponType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends BaseRepository<Coupon>{

    List<Coupon> findByArrangementNo(Long arrNo);
    List<Coupon> findByType(CouponType type);
    List<Coupon> findByYearNo(int yearNo);
    List<Coupon> findByStockNo_Id(Long id);

    //used! should add

}
