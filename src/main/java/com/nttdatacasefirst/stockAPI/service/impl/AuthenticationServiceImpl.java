package com.nttdatacasefirst.stockAPI.service.impl;

import com.nttdatacasefirst.stockAPI.dtos.AuthenticationRequest;
import com.nttdatacasefirst.stockAPI.dtos.AuthenticationResponse;
import com.nttdatacasefirst.stockAPI.dtos.RegisterRequest;
import com.nttdatacasefirst.stockAPI.entity.User;
import com.nttdatacasefirst.stockAPI.entity.enums.Role;
import com.nttdatacasefirst.stockAPI.repository.UserRepository;
import com.nttdatacasefirst.stockAPI.service.AuthenticationService;
import com.nttdatacasefirst.stockAPI.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService serviceJwt;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(@Autowired UserRepository repository,
                                     @Autowired PasswordEncoder passwordEncoder,
                                     @Autowired JwtService serviceJwt,
                                     @Autowired AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.serviceJwt = serviceJwt;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthenticationResponse register(RegisterRequest request){
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastname(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        repository.save(user);

        var jwtToken = serviceJwt.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();

        var jwtToken = serviceJwt.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
