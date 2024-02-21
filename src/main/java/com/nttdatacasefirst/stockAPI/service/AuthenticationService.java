package com.nttdatacasefirst.stockAPI.service;

import com.nttdatacasefirst.stockAPI.dtos.AuthenticationRequest;
import com.nttdatacasefirst.stockAPI.dtos.AuthenticationResponse;
import com.nttdatacasefirst.stockAPI.dtos.RegisterRequest;

public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    String changeRole(String role);
}
