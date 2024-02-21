package com.nttdatacasefirst.stockAPI.controller;

import com.nttdatacasefirst.stockAPI.dtos.*;
import com.nttdatacasefirst.stockAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService serviceUser;

    public UserController(@Autowired UserService serviceUser) {
        this.serviceUser = serviceUser;
    }

    @GetMapping("/getInfo")
    public ResponseEntity<UserGetModel> getUserInformations(){
        return ResponseEntity.ok(serviceUser.getUserInformation());
    }

    @PutMapping("/setShareholder")
    @ResponseBody
    public ResponseEntity<UserGetModel> setShareholderInfo(@RequestBody ShareholderUpdateByUserModel updateModel){
        return ResponseEntity.ok(serviceUser.setShareholder(updateModel));
    }

    @GetMapping("/getStocks")
    public ResponseEntity<List<StockGetModel>> getAllStocksUser(){
        return ResponseEntity.ok(serviceUser.getAllStocks());
    }

    @GetMapping("/getCapitalIncrease")
    public ResponseEntity<List<CapitalIncreaseGetModel>> getAllCapital(){
        return ResponseEntity.ok(serviceUser.getAllCapital());
    }

    @GetMapping("/getDividentDist")
    public ResponseEntity<List<DividendDistributionGetModel>> getAllDividendDist(){
        return ResponseEntity.ok(serviceUser.getAllDivident());
    }

    @GetMapping("/getOperations")
    public ResponseEntity<List<OperationGetModel>> getAllOperationsUser(){
        return ResponseEntity.ok(serviceUser.getAllOperationsforShareholder());
    }

    @PostMapping("/buyStock")
    public ResponseEntity<OperationUserGetModel> buyStock(@RequestBody OperationUserAddModel addModel){
        return ResponseEntity.ok(serviceUser.buyStockOperation(addModel));
    }
    @PostMapping("/sellStock")
    public ResponseEntity<OperationUserGetModel> sellStock(@RequestBody OperationUserAddModel addModel){
        return ResponseEntity.ok(serviceUser.sellStockOperation(addModel));
    }

    @PostMapping("/dividendDist")
    public ResponseEntity<OperationUserGetModel> makeDividendDist(@RequestBody OperationUserAddModel addModel){
        return ResponseEntity.ok(serviceUser.dividendDistributionForUser(addModel));
    }

    @PutMapping("/addMoney")
    public ResponseEntity<String> addMoneyRequest(@RequestBody ShareholderRequestModel addModel){
        return ResponseEntity.ok(serviceUser.addMoneyToRequest(addModel));
    }
}


