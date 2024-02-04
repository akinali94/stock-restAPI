package com.nttdatacasefirst.stockAPI.service.impl;


import com.nttdatacasefirst.stockAPI.dtos.ShareholderAddModel;
import com.nttdatacasefirst.stockAPI.dtos.ShareholderGetModel;
import com.nttdatacasefirst.stockAPI.dtos.ShareholderUpdateModel;
import com.nttdatacasefirst.stockAPI.entity.ShareHolder;
import com.nttdatacasefirst.stockAPI.entity.enums.InvestorType;
import com.nttdatacasefirst.stockAPI.exceptions.ShareholderAlreadyExistException;
import com.nttdatacasefirst.stockAPI.exceptions.ShareholderNotFoundException;
import com.nttdatacasefirst.stockAPI.mapper.MapperShareholder;
import com.nttdatacasefirst.stockAPI.repository.ShareholderRepository;
import com.nttdatacasefirst.stockAPI.service.ShareholderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShareholderServiceImpl implements ShareholderService {
    private final ShareholderRepository repositoryShareholder;
    private final MapperShareholder mapperShareholder;

    public ShareholderServiceImpl(@Autowired ShareholderRepository repositoryShareholder,
                                  @Autowired MapperShareholder mapperShareholder){

        this.repositoryShareholder = repositoryShareholder;
        this.mapperShareholder = mapperShareholder;
    }
    @Override
    public ShareholderGetModel addShareHolder(ShareholderAddModel addModel) {

        ShareHolder checkShareholder = repositoryShareholder.findShareHolderByRegistorNo(addModel.getRegNo());
        if(checkShareholder != null)
            throw new ShareholderAlreadyExistException("Shareholder already exist");

        ShareHolder newShareholder = mapperShareholder.toAdd(addModel);

        repositoryShareholder.save(newShareholder);

        return mapperShareholder.toModelGet(newShareholder);
    }

    @Override
    public ShareholderGetModel updateShareHolder(ShareholderUpdateModel updateModel) {

        ShareHolder findShareholder = repositoryShareholder.findShareHolderByRegistorNo(updateModel.getRegNo());

        if(findShareholder == null)
            throw new ShareholderNotFoundException("Shareholder Not Found");

        ShareHolder updatedShareholder = mapperShareholder.toUpdate(updateModel);

        repositoryShareholder.save(updatedShareholder);

        return mapperShareholder.toModelGet(updatedShareholder);
    }

    @Override
    public ShareholderGetModel deleteShareHolder(String id) {

        ShareHolder findShareholder = repositoryShareholder.findShareHolderByRegistorNo(Long.parseLong(id));

        if(findShareholder == null)
            throw new ShareholderNotFoundException("Shareholder Not Found");

        ShareholderGetModel mapped = mapperShareholder.toModelGet(findShareholder);

        repositoryShareholder.deleteById(findShareholder.getRegistorNo());

        return mapped;
    }

    @Override
    public List<ShareholderGetModel> listAllShareholders() {
        List<ShareHolder> getAllShareholder = repositoryShareholder.findAll();
        if(getAllShareholder.isEmpty())
            throw new ShareholderNotFoundException("There is no Shareholder");

        return mapperShareholder.toModelGetList(getAllShareholder);
    }

    @Override
    public ShareholderGetModel findShareholderByRegNo(String regNo) {
        Long regNoLong = Long.parseLong(regNo);
        if(repositoryShareholder.findShareHolderByRegistorNo(regNoLong) == null)
            throw new ShareholderNotFoundException("Shareholder Not found");

        return mapperShareholder.toModelGet(repositoryShareholder.findShareHolderByRegistorNo(regNoLong));

    }

    @Override
    public ShareholderGetModel findShareholderByTitle(String title) {
        ShareHolder getShareholder = repositoryShareholder.findShareHolderByTitle(title);
        if(getShareholder == null)
            throw new ShareholderNotFoundException("There is no Shareholder");

        return mapperShareholder.toModelGet(getShareholder);
    }

    @Override
    public ShareholderGetModel findShareholderByAddress(String address) {
        ShareHolder getShareholder = repositoryShareholder.findShareHolderByAddress(address);
        if(getShareholder == null)
            throw new ShareholderNotFoundException("There is no Shareholder");

        return mapperShareholder.toModelGet(getShareholder);
    }

    @Override
    public ShareholderGetModel findShareholderByPhone(String phoneNo) {
        ShareHolder getShareholder = repositoryShareholder.findShareHolderByPhoneNo(phoneNo);
        if(getShareholder == null)
            throw new ShareholderNotFoundException("There is no Shareholder");

        return mapperShareholder.toModelGet(getShareholder);
    }

    @Override
    public List<ShareholderGetModel> findByInvestorType(String investorType) {
        List<ShareHolder> getShareholder = repositoryShareholder.findByInvestorType(InvestorType.valueOf(investorType));
        if(getShareholder.isEmpty())
            throw new ShareholderNotFoundException("There is no Shareholder");

        return mapperShareholder.toModelGetList(getShareholder);
    }

    @Override
    public List<ShareholderGetModel> haveStock() {
        List<ShareHolder> getShareholder = repositoryShareholder.findByStockListIsNotNull();
        if(getShareholder.isEmpty())
            throw new ShareholderNotFoundException("There is no Shareholder");

        return mapperShareholder.toModelGetList(getShareholder);
    }

    @Override
    public List<ShareholderGetModel> haveNotStock() {
        List<ShareHolder> getShareholder = repositoryShareholder.findByStockListIsNull();
        if(getShareholder.isEmpty())
            throw new ShareholderNotFoundException("There is no Shareholder");

        return mapperShareholder.toModelGetList(getShareholder);
    }

    @Override
    public ShareHolder getShareholderForOperations(String regNo) {
        return repositoryShareholder.findById(Long.parseLong(regNo))
                .orElseThrow(() -> new ShareholderNotFoundException("Shareholder Not Found"));
    }

}
