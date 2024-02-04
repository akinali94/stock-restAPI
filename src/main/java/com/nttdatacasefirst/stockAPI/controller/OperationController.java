package com.nttdatacasefirst.stockAPI.controller;

import com.nttdatacasefirst.stockAPI.dtos.OperationAddModel;
import com.nttdatacasefirst.stockAPI.dtos.OperationGetModel;
import com.nttdatacasefirst.stockAPI.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/operations")
public class OperationController {
    private final OperationService serviceOperation;

    public OperationController(@Autowired OperationService operationService){
        this.serviceOperation = operationService;
    }

    public ResponseEntity<OperationGetModel> addOperation(OperationAddModel addModel){
        return ResponseEntity.ok(serviceOperation.addOperation(addModel));
    }


}

