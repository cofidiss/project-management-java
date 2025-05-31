package com.projectmanagement.service.implementation;

import com.projectmanagement.service.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class JwtUtil {
   private  JwtConfig jwtConfig;
public JwtUtil(JwtConfig jwtConfig){
this.jwtConfig = jwtConfig;
}
    public String generateToken(long userId) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpirationTime()))
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getSecret())
                .compact();
    }

    public Long extractUserId(String token) {
        try {
          return  Long.parseLong(extractAllClaims(token).getSubject());
        } catch (Exception e) {
            return null;
        }
    }

    public boolean validateToken(String token, Long userId) {
        return  (Objects.equals(extractUserId(token), userId)) && !isTokenExpired(token);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(jwtConfig.getSecret()).parseClaimsJws(token).getBody();
    }

    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}
