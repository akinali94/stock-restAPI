package com.nttdatacasefirst.stockAPI.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    String extractUsername(String jwt);
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);
    String generateToken(UserDetails  userDetails);
    public boolean isTokenValid(String token, UserDetails userDetails);

}
