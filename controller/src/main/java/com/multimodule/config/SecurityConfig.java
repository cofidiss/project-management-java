package com.multimodule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF devre dışı
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()  // tüm isteklere izin ver
                )
                .httpBasic(Customizer.withDefaults()); // basic auth (zorunlu değil ama log için basit)

        return http.build();
    }
}