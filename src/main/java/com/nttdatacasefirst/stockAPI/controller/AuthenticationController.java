package com.nttdatacasefirst.stockAPI.controller;

import com.nttdatacasefirst.stockAPI.dtos.AuthenticationRequest;
import com.nttdatacasefirst.stockAPI.dtos.AuthenticationResponse;
import com.nttdatacasefirst.stockAPI.dtos.RegisterRequest;
import com.nttdatacasefirst.stockAPI.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService service;

    public AuthenticationController(@Autowired AuthenticationService service) {
        this.service = service;
    }


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){

        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){

        return ResponseEntity.ok(service.authenticate(request));
    }

    //silinecek
    @PutMapping("/change/{role}")
    public ResponseEntity<String> changeRole(@PathVariable String role ){
        return ResponseEntity.ok(service.changeRole(role));
    }

}
