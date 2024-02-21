package com.nttdatacasefirst.stockAPI.service;

import com.nttdatacasefirst.stockAPI.dtos.ShareholderGetModel;

public interface AdminService {
    ShareholderGetModel changeShareholderRequestToAmount(String regNo);
}
