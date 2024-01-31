package com.nttdatacasefirst.stockAPI.repository;

import com.nttdatacasefirst.stockAPI.entity.ShareHolder;
import com.nttdatacasefirst.stockAPI.entity.enums.InvestorType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShareholderRepository extends BaseRepository<ShareHolder> {

    ShareHolder findShareHolderByTitle(String title);
    ShareHolder findShareHolderByRegistorNo(Long regNo);
    ShareHolder findShareHolderByAddress(String address);
    ShareHolder findShareHolderByPhoneNo(String phone);
    List<ShareHolder> findByInvestorType(InvestorType investorType);
    List<ShareHolder> findByStockListIsNotNull();
    List<ShareHolder> findByStockListIsNull();

}
