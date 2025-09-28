package com.brandenarms.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

@Service
public class AuthService {
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.H256);

    public String generateToken(String username) {
        long expirationMillis = 1000 * 60 * 60;

        return Jwt.builder()
                .setSubject(username)
                .setIssued(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(key)
                .compact();
    }
}