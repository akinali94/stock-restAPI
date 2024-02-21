package com.nttdatacasefirst.stockAPI.mapper.impl;

import com.nttdatacasefirst.stockAPI.dtos.UserGetModel;
import com.nttdatacasefirst.stockAPI.entity.User;
import com.nttdatacasefirst.stockAPI.mapper.MapperUser;
import org.springframework.stereotype.Component;

@Component
public class MapperUserImpl implements MapperUser {

    @Override
    public UserGetModel toModelGet(User user) {

        UserGetModel getUser = new UserGetModel();

        getUser.setUserEmail(user.getEmail());
        getUser.setUserRole(user.getRole().getLabel());
        getUser.setRegisterNo(Long.toString(user.getId()));
        getUser.setTitle(user.getShareHolder().getTitle());
        getUser.setAddress(user.getShareHolder().getAddress());
        getUser.setPhoneNo(user.getShareHolder().getPhoneNo());
        getUser.setInverstorType(user.getShareHolder().getInvestorType().getLabel());

        return getUser;
    }
}
