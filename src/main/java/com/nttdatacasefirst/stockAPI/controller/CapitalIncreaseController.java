package com.nttdatacasefirst.stockAPI.controller;


import com.nttdatacasefirst.stockAPI.dtos.CapitalIncreaseAddModel;
import com.nttdatacasefirst.stockAPI.dtos.CapitalIncreaseGetModel;
import com.nttdatacasefirst.stockAPI.service.CapitalIncreaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/capitalIncrease")
public class CapitalIncreaseController {

    private final CapitalIncreaseService serviceCapitalIncrease;

    public CapitalIncreaseController(@Autowired CapitalIncreaseService serviceCapitalIncrease){
        this.serviceCapitalIncrease = serviceCapitalIncrease;
    }

    @PostMapping("/add")
    public ResponseEntity<CapitalIncreaseGetModel> addCapitalIncrease(@RequestBody CapitalIncreaseAddModel addModel){

        return ResponseEntity.ok(serviceCapitalIncrease.addCapitalIncrease(addModel));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CapitalIncreaseGetModel>> getAllCapitalIncrease(){

        return ResponseEntity.ok(serviceCapitalIncrease.getAllCapitalIncrease());
    }

}
