package com.nttdatacasefirst.stockAPI.controller;

import com.nttdatacasefirst.stockAPI.dtos.ShareholderGetModel;
import com.nttdatacasefirst.stockAPI.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService serviceAdmin;

    public AdminController(@Autowired AdminService serviceAdmin) {
        this.serviceAdmin = serviceAdmin;
    }

    @PutMapping("/shareholderRequest/{id}")
    public ResponseEntity<ShareholderGetModel> changeShareholderRequestToAmount(@PathVariable String id){

        return ResponseEntity.ok(serviceAdmin.changeShareholderRequestToAmount(id));
    }

}
