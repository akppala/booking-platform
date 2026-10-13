package com.booking.user.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;




@Service
public class JWTService {

    private static final String SECRET =
            "thisIsVeryLongSecretKeyForJwtTokenGeneration123456";

    private final SecretKey key =
            Keys.hmacShaKeyFor(
                    SECRET.getBytes());

    public String generateToken(
            String username) {

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 86400000))
                .signWith(key)
                .compact();
    }
}
