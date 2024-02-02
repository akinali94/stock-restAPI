package com.nttdatacasefirst.stockAPI.controller;

import com.nttdatacasefirst.stockAPI.dtos.StockAddModel;
import com.nttdatacasefirst.stockAPI.dtos.StockGetModel;
import com.nttdatacasefirst.stockAPI.service.StockService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {

    private final StockService serviceStock;

    public StockController(@Autowired StockService serviceStock) {
        this.serviceStock = serviceStock;
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<StockGetModel> createStock(@RequestBody StockAddModel addModel){

        return ResponseEntity.ok(serviceStock.addStock(addModel));
    }

    @GetMapping("/search/all")
    public ResponseEntity<List<StockGetModel>> getAllStocks(){
        return ResponseEntity.ok(serviceStock.getAllStocks());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<List<StockGetModel>> getStockByCapitalIncrease(@PathVariable String id){
        return ResponseEntity.ok(serviceStock.getStocksofCapitalIncrease(id));
    }
}
