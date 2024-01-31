package com.nttdatacasefirst.stockAPI.mapper;

import com.nttdatacasefirst.stockAPI.dtos.ShareholderAddModel;
import com.nttdatacasefirst.stockAPI.dtos.ShareholderGetModel;
import com.nttdatacasefirst.stockAPI.dtos.ShareholderUpdateModel;
import com.nttdatacasefirst.stockAPI.entity.ShareHolder;

import java.util.List;

public interface MapperShareholder {

    ShareHolder toAdd(ShareholderAddModel addModel);

    ShareHolder toUpdate(ShareholderUpdateModel updateModel);

    ShareholderGetModel toModelGet(ShareHolder shareHolder);

    List<ShareholderGetModel> toModelGetList(List<ShareHolder> shareHolders);
    ShareholderUpdateModel toModelUpdate(ShareHolder shareHolder);
}
