package com.nttdatacasefirst.stockAPI.controller;

import com.nttdatacasefirst.stockAPI.dtos.DividendDistributionAddModel;
import com.nttdatacasefirst.stockAPI.dtos.DividendDistributionGetModel;
import com.nttdatacasefirst.stockAPI.entity.DividendDistribution;
import com.nttdatacasefirst.stockAPI.service.DividendDistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dividentDistribution")
public class DividendDistributionController {

    private final DividendDistributionService service;

    public DividendDistributionController(@Autowired DividendDistributionService service ){
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<DividendDistributionGetModel> addDividendDistribution(@RequestBody DividendDistributionAddModel addModel){
        return ResponseEntity.ok(service.addDividendDistribution(addModel));
    }

    @GetMapping("search/{arrNo}")
    public ResponseEntity<List<DividendDistributionGetModel>> getDividendDistributionByCapitalIncrease(@PathVariable Long arrNo){
        return ResponseEntity.ok(service.getDividendDistrubitonByCapitalIncrease(arrNo));
    };

    @GetMapping("search/all")
    public ResponseEntity<List<DividendDistributionGetModel>> getAllDividendDistribution(){
        return ResponseEntity.ok(service.getAllDividendDistribution());
    }

}
