package com.nttdatacasefirst.stockAPI.mapper;

import com.nttdatacasefirst.stockAPI.dtos.OperationUserGetModel;
import com.nttdatacasefirst.stockAPI.dtos.UserGetModel;
import com.nttdatacasefirst.stockAPI.entity.User;

public interface MapperUser {
    UserGetModel toModelGet(User user);
}
