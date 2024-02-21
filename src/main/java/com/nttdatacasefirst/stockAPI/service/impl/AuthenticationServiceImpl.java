package com.nttdatacasefirst.stockAPI.service.impl;

import com.nttdatacasefirst.stockAPI.dtos.AuthenticationRequest;
import com.nttdatacasefirst.stockAPI.dtos.AuthenticationResponse;
import com.nttdatacasefirst.stockAPI.dtos.RegisterRequest;
import com.nttdatacasefirst.stockAPI.entity.ShareHolder;
import com.nttdatacasefirst.stockAPI.entity.User;
import com.nttdatacasefirst.stockAPI.entity.enums.InvestorType;
import com.nttdatacasefirst.stockAPI.entity.enums.Role;
import com.nttdatacasefirst.stockAPI.exceptions.MailAlreadyRegisteredException;
import com.nttdatacasefirst.stockAPI.repository.UserRepository;
import com.nttdatacasefirst.stockAPI.service.AuthenticationService;
import com.nttdatacasefirst.stockAPI.service.JwtService;
import com.nttdatacasefirst.stockAPI.service.ShareholderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService serviceJwt;
    private final AuthenticationManager authenticationManager;
    private final ShareholderService serviceShareholder;

    public AuthenticationServiceImpl(@Autowired UserRepository repository,
                                     @Autowired PasswordEncoder passwordEncoder,
                                     @Autowired JwtService serviceJwt,
                                     @Autowired AuthenticationManager authenticationManager,
                                     @Autowired ShareholderService serviceShareholder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.serviceJwt = serviceJwt;
        this.authenticationManager = authenticationManager;
        this.serviceShareholder = serviceShareholder;
    }

    @Override
    public AuthenticationResponse register(RegisterRequest request){
        //Check email
        if(repository.findByEmail(request.getEmail()).isPresent())
            throw new MailAlreadyRegisteredException("This email is using by another user");

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastname(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        User newUser = repository.save(user);

        //Set One-to-One Relationship
        //Note: Problem can occur from User, because user may not aware of this shareholder
        ShareHolder newShareholder = new ShareHolder();
        newShareholder.setUser(newUser);
        newShareholder.setInvestorType(InvestorType.NOTDETERMINED);
        serviceShareholder.addShareholderFromUser(newShareholder);

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

    @Override
    public String changeRole(String role) {
        User user = repository.findByEmail("deneme@deneme.com").orElseThrow(() -> new UsernameNotFoundException("deneme"));
        try{
            if(role.equals("ADMIN"))
                user.setRole(Role.ADMIN);
            if(role.equals("USER"))
                user.setRole(Role.USER);
            repository.save(user);
            return "degisim basarili" ;
        } catch (Exception e){
            return "olmadi";
        }

    }
}
