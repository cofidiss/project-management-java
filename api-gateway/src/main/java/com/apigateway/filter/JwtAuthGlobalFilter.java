package com.apigateway.filter;

import com.apigateway.util.GatewayJwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthGlobalFilter implements GlobalFilter {

    private static final String USER_ID_HEADER = "X-User-Id";

    private static final List<String> PUBLIC_PATH_PREFIXES = List.of(
            "/api/user/login",
            "/api/user/signup",
            "/swagger-ui",
            "/api-docs"
    );

    private final GatewayJwtUtil jwtUtil;

    @Value("${security.jwt.cookie-name:jwt}")
    private String cookieName;

    @Value("${security.gateway.shared-secret}")
    private String gatewaySharedSecret;

    @Value("${security.gateway.shared-header:X-Gateway-Auth}")
    private String gatewaySharedHeader;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();

        if (isPublicPath(path) || HttpMethod.OPTIONS.equals(exchange.getRequest().getMethod())) {

            ServerHttpRequest request = exchange.getRequest()
                    .mutate()
                    .headers(headers -> {
                        headers.remove(gatewaySharedHeader);
                        headers.set(gatewaySharedHeader, gatewaySharedSecret);
                    })
                    .build();

            return chain.filter(exchange.mutate().request(request).build());
        }

        HttpCookie cookie = exchange.getRequest().getCookies().getFirst(cookieName);

        if (cookie == null || !StringUtils.hasText(cookie.getValue())) {
            return unauthorized(exchange);
        }

        String token = cookie.getValue();

        try {

            if (jwtUtil.isTokenExpired(token)) {
                return unauthorized(exchange);
            }

            Long userId = jwtUtil.extractUserId(token);

            if (userId == null) {
                return unauthorized(exchange);
            }

            ServerHttpRequest request = exchange.getRequest()
                    .mutate()
                    .headers(headers -> {
                        headers.remove(USER_ID_HEADER);
                        headers.remove(gatewaySharedHeader);

                        headers.set(USER_ID_HEADER, String.valueOf(userId));
                        headers.set(gatewaySharedHeader, gatewaySharedSecret);
                    })
                    .build();

            return chain.filter(exchange.mutate().request(request).build());

        } catch (Exception ex) {
            log.warn("JWT validation failed: {}", ex.getMessage());
            return unauthorized(exchange);
        }
    }

    private boolean isPublicPath(String path) {
        return PUBLIC_PATH_PREFIXES.stream().anyMatch(path::startsWith);
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}
