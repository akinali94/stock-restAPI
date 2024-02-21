package com.nttdatacasefirst.stockAPI.service;

import com.nttdatacasefirst.stockAPI.dtos.*;
import com.nttdatacasefirst.stockAPI.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {
    User getUserDirectly();
    UserGetModel getUserInformation();
    UserGetModel setShareholder(ShareholderUpdateByUserModel updateModel);
    List<StockGetModel> getAllStocks();
    List<CapitalIncreaseGetModel> getAllCapital();
    List<DividendDistributionGetModel> getAllDivident();
    List<OperationGetModel> getAllOperationsforShareholder();

    OperationUserGetModel buyStockOperation(OperationUserAddModel addModel);
    OperationUserGetModel sellStockOperation(OperationUserAddModel addModel);
    OperationUserGetModel dividendDistributionForUser(OperationUserAddModel addModel);

    void subtractMoney(User user, BigDecimal money);
    void addMoney(User user, BigDecimal money);

    String addMoneyToRequest(ShareholderRequestModel request);

}
