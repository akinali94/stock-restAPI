package com.nttdatacasefirst.stockAPI.controller;

import com.nttdatacasefirst.stockAPI.dtos.OperationAddModel;
import com.nttdatacasefirst.stockAPI.dtos.OperationGetModel;
import com.nttdatacasefirst.stockAPI.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operation")
public class OperationController {
    private final OperationService serviceOperation;

    public OperationController(@Autowired OperationService operationService){
        this.serviceOperation = operationService;
    }
    @PostMapping("/add")
    public ResponseEntity<OperationGetModel> addOperation(@RequestBody OperationAddModel addModel){
        return ResponseEntity.ok(serviceOperation.addOperation(addModel));
    }

    @GetMapping("/search/all")
    public ResponseEntity<List<OperationGetModel>> getAllOperations(){
        return ResponseEntity.ok(serviceOperation.getAllOperations());
    }


}

