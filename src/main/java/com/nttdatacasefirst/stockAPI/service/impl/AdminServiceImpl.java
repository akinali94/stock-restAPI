package com.nttdatacasefirst.stockAPI.service.impl;

import com.nttdatacasefirst.stockAPI.dtos.ShareholderGetModel;
import com.nttdatacasefirst.stockAPI.entity.ShareHolder;
import com.nttdatacasefirst.stockAPI.mapper.MapperShareholder;
import com.nttdatacasefirst.stockAPI.service.AdminService;
import com.nttdatacasefirst.stockAPI.service.ShareholderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AdminServiceImpl implements AdminService {
    private final ShareholderService serviceShareholder;
    private final MapperShareholder mapperShareholder;

    public AdminServiceImpl(@Autowired ShareholderService serviceShareholder,
                            @Autowired MapperShareholder mapperShareholder) {
        this.serviceShareholder = serviceShareholder;
        this.mapperShareholder = mapperShareholder;
    }

    @Override
    public ShareholderGetModel changeShareholderRequestToAmount(String regNo) {

        ShareHolder shareHolder = serviceShareholder.findShareholderForOperations(regNo);

        BigDecimal requestedAmount = shareHolder.getRequestMoney();
        shareHolder.setAmountMoney(requestedAmount);
        shareHolder.setRequestMoney(new BigDecimal(0));

        return mapperShareholder.toModelGet(serviceShareholder.saveShareholder(shareHolder));

    }


}
