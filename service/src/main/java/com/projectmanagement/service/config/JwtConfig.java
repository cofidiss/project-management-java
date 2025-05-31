package com.projectmanagement.service.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class JwtConfig {

    @Value("${security.jwt.secret}")
    private String secret;
    @Value("${security.jwt.expirationTime}")
    private String expirationTime;

    public Long getExpirationTime() {
        return Long.parseLong(expirationTime);
    }
}