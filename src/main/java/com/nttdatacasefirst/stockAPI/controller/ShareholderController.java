package com.nttdatacasefirst.stockAPI.controller;

import com.nttdatacasefirst.stockAPI.dtos.*;
import com.nttdatacasefirst.stockAPI.service.ShareholderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequestMapping("/shareholders")
public class ShareholderController {
    private final ShareholderService serviceShareholders;

    public ShareholderController(@Autowired ShareholderService serviceShareholders){
        this.serviceShareholders = serviceShareholders;
    }

    @GetMapping("search/all")
    public ResponseEntity<List<ShareholderGetModel>> findAllShareholder(){

        return ResponseEntity.ok(serviceShareholders.listAllShareholders());
    }

    @GetMapping("search/{id}")
    public ResponseEntity<ShareholderGetModel> findShareholderById(@PathVariable String id){

        return ResponseEntity.ok(serviceShareholders.findShareholderByRegNo(id));

    }

    @PostMapping("/add")
    public ResponseEntity<ShareholderGetModel> createShareholder(@RequestBody ShareholderAddModel addModel) {

        return ResponseEntity.ok(serviceShareholders.addShareHolder(addModel));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ShareholderGetModel> deleteShareholder(@PathVariable String id){

        return ResponseEntity.ok(serviceShareholders.deleteShareHolder(id));
    }

    @PutMapping("/update")
    public ResponseEntity<ShareholderGetModel> updateShareholder(@RequestBody ShareholderUpdateModel updateModel){

        return ResponseEntity.ok(serviceShareholders.updateShareHolder(updateModel));
    }

    @GetMapping("search/{title}")
    public ResponseEntity<ShareholderGetModel> findShareholderByTitle(@PathVariable String title){
        return ResponseEntity.ok(serviceShareholders.findShareholderByTitle(title));
    }

    @GetMapping("search/{address}")
    public ResponseEntity<ShareholderGetModel> findShareholderByAddress(@PathVariable String address){
        return ResponseEntity.ok(serviceShareholders.findShareholderByAddress(address));
    }

    @GetMapping("search/{phone}")
    public ResponseEntity<ShareholderGetModel> findShareholderByPhone(@PathVariable String phone){
        return ResponseEntity.ok(serviceShareholders.findShareholderByPhone(phone));
    }

    @GetMapping("search/{type}")
    public ResponseEntity<List<ShareholderGetModel>> findShareholderByInvestorType(@PathVariable String type){
        return ResponseEntity.ok(serviceShareholders.findByInvestorType(type));
    }

    @GetMapping("search/hasStock")
    public ResponseEntity<List<ShareholderGetModel>> findShareholderHasStock(){
        return ResponseEntity.ok(serviceShareholders.haveStock());
    }

    @GetMapping("search/hasNotStock")
    public ResponseEntity<List<ShareholderGetModel>> findShareholderHasNotStock(){
        return ResponseEntity.ok(serviceShareholders.haveNotStock());
    }

}
