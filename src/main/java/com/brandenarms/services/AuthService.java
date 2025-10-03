package com.brandenarms.services;

import com.brandenarms.models.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.brandenarms.dtos.AuthUserDTO;

import java.security.Key;
import java.util.Date;
import java.util.Objects;

@Service
public class AuthService {
    private Key key;
    private long expiration;

    public AuthService(@Value("${jwt.secret}")String secret,
                       @Value("${jwt.expiration}")long expiration) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.expiration  = expiration;
    }

    public String generateToken(AuthUserDTO dto) {
        return Jwts.builder()
                .setSubject(dto.username())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key)
                .claim("userId", dto.id())
                .compact();
    }

    public AuthUserDTO extractUser(String token) {
        Claims claims = parseClaims(token);
        Long id = claims.get("userId", Long.class);
        String username = claims.getSubject();
        return new AuthUserDTO(id, username);
    }

    public boolean isTokenValid(String token, User user) {
        try {
            Claims claims = parseClaims(token);
            return Objects.equals(user.getEmail(), claims.getSubject())
                    && !claims.getExpiration().before(new Date());
        } catch (JwtException e) {
            return false;
        }
    }

    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
       return parseClaims(token).getSubject();
    }

}