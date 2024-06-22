package com.example.L2.S2.Project.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private final String SECRETKEY = "3f5b1874f9fde5de2c0d2cdf8cb904ac9c8b50b3b652fc0fde091ef922b7c26e59ac399f6aebbb976d79545ef4885759edd5e8a9a4ad7733ce186ee46e511a247e0f06064b33a2aaebd90eecfa61a4b95c59583f18bc88bbd3f43db6f99af7cff4fb2df5c9abe0a11c5a07efe1c75c9191dbc1336299510c73027ece2cda5d11558b64ce40ca0ba200134c1dcb76dc6f6a66748db5be09edb63d391c2af760036cf3b0b0211521d756d4513fddebb5398095e38c625e505aa91b32df616a8644b7199095aecba5293f540c02fe844f714cfc0f5e2b17d5332f616db0d2a4d247f91e475668931010dd0812022aac3c209f8c3bcf1b98d3f2fda7755d0f3b9885";

    public String extractUserName(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    private String generateToken(Map<String, Object> extractClaims, UserDetails userDetails) {
        return Jwts.builder().setClaims(extractClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256).compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {

        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);

    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    private Key getSigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRETKEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJwt(token)
                .getBody();
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolvers) {

        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

}
