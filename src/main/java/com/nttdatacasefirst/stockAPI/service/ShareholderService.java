package com.nttdatacasefirst.stockAPI.service;

import com.nttdatacasefirst.stockAPI.dtos.*;
import com.nttdatacasefirst.stockAPI.entity.ShareHolder;

import java.math.BigDecimal;
import java.util.List;

public interface ShareholderService {
    ShareholderGetModel addShareHolder(ShareholderAddModel addModel);
    ShareholderGetModel updateShareHolder(ShareholderUpdateModel updateModel);
    ShareholderGetModel deleteShareHolder(String id);
    List<ShareholderGetModel> listAllShareholders();
    ShareholderGetModel findShareholderByRegNo(String regNo);
    ShareholderGetModel findShareholderByTitle(String title);
    ShareholderGetModel findShareholderByAddress(String address);
    ShareholderGetModel findShareholderByPhone(String phoneNo);
    List<ShareholderGetModel> findByInvestorType(String investorType);
    List<ShareholderGetModel> haveStock();
    List<ShareholderGetModel> haveNotStock();

    ShareHolder findShareholderForOperations(String regNo);
    ShareHolder addShareholderFromUser(ShareHolder shareholder);


    ShareHolder saveShareholder(ShareHolder shareholder);

}
