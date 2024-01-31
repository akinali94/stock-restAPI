package com.nttdatacasefirst.stockAPI.mapper.impl;

import com.nttdatacasefirst.stockAPI.dtos.*;
import com.nttdatacasefirst.stockAPI.entity.ShareHolder;
import com.nttdatacasefirst.stockAPI.entity.enums.InvestorType;
import com.nttdatacasefirst.stockAPI.mapper.MapperShareholder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperShareholderImpl implements MapperShareholder {

    public ShareHolder toAdd(ShareholderAddModel addModel){
        ShareHolder newShareholder = new ShareHolder();
        newShareholder.setRegistorNo(addModel.getRegNo());
        newShareholder.setTitle(addModel.getTitle());
        newShareholder.setPhoneNo(addModel.getPhoneNo());
        newShareholder.setInvestorType(InvestorType.valueOf(addModel.getInvestorType()));
        newShareholder.setAddress(addModel.getAddress());

        return newShareholder;
    }

    public ShareHolder toUpdate(ShareholderUpdateModel updateModel){
        ShareHolder newShareholder = new ShareHolder();
        newShareholder.setRegistorNo(updateModel.getRegNo());
        newShareholder.setTitle(updateModel.getTitle());
        newShareholder.setPhoneNo(updateModel.getPhoneNo());
        newShareholder.setInvestorType(InvestorType.valueOf(updateModel.getInvestorType()));
        newShareholder.setAddress(updateModel.getAddress());
        return newShareholder;
    }

    public ShareholderGetModel toModelGet(ShareHolder shareHolder){
        ShareholderGetModel newGetModel = new ShareholderGetModel();

        newGetModel.setRegisterNo(shareHolder.getRegistorNo());
        newGetModel.setTitle(shareHolder.getTitle());
        newGetModel.setAddress(shareHolder.getAddress());
        newGetModel.setPhoneNo(shareHolder.getPhoneNo());
        newGetModel.setInvestorType(shareHolder.getInvestorType().getLabel());

        return newGetModel;
    }

    public List<ShareholderGetModel> toModelGetList(List<ShareHolder> shareHolders){
        return shareHolders.stream()
                .map(x -> new ShareholderGetModel(
                        x.getRegistorNo(),
                        x.getTitle(),
                        x.getAddress(),
                        x.getPhoneNo(),
                        x.getInvestorType().getLabel()))
                .collect(Collectors.toList());
    }
    public ShareholderUpdateModel toModelUpdate(ShareHolder shareHolder){
        ShareholderUpdateModel newUpdateModel = new ShareholderUpdateModel();
        newUpdateModel.setRegNo(shareHolder.getRegistorNo());
        newUpdateModel.setTitle(shareHolder.getTitle());
        newUpdateModel.setPhoneNo(shareHolder.getPhoneNo());
        newUpdateModel.setInvestorType(shareHolder.getInvestorType().getLabel());

        return newUpdateModel;
    }
}
