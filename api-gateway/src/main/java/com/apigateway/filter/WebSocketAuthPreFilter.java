package com.apigateway.filter;


import com.apigateway.util.GatewayJwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * WebSocket Authentication Pre-Filter
 *
 * Handles JWT authentication for WebSocket connections.
 * Validates JWT from cookie (same as JwtAuthGlobalFilter),
 * then injects X-User-Id and X-Gateway-Auth headers before forwarding.
 */
@Component
@Slf4j
public class WebSocketAuthPreFilter implements GlobalFilter, Ordered {

    private static final String USER_ID_HEADER = "X-User-Id";

    private final GatewayJwtUtil jwtUtil;

    @Value("${security.jwt.cookie-name:jwt}")
    private String cookieName;

    @Value("${security.gateway.shared-secret}")
    private String gatewaySharedSecret;

    @Value("${security.gateway.shared-header:X-Gateway-Auth}")
    private String gatewaySharedHeader;

    public WebSocketAuthPreFilter(GatewayJwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        if (!path.startsWith("/ws")) {
            return chain.filter(exchange);
        }

        HttpCookie cookie = exchange.getRequest().getCookies().getFirst(cookieName);
        if (cookie == null || !StringUtils.hasText(cookie.getValue())) {
            log.warn("WebSocket: no JWT cookie, path={}", path);
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = cookie.getValue();
        try {
            if (jwtUtil.isTokenExpired(token)) {
                log.warn("WebSocket: expired JWT token");
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            Long userId = jwtUtil.extractUserId(token);
            if (userId == null) {
                log.warn("WebSocket: userId not found in token");
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            log.info("WebSocket authenticated - userId={}, path={}", userId, path);

            ServerHttpRequest mutatedRequest = exchange.getRequest()
                    .mutate()
                    .headers(headers -> {
                        headers.remove(USER_ID_HEADER);
                        headers.remove(gatewaySharedHeader);
                        headers.set(USER_ID_HEADER, String.valueOf(userId));
                        headers.set(gatewaySharedHeader, gatewaySharedSecret);
                    })
                    .build();

            return chain.filter(exchange.mutate().request(mutatedRequest).build());

        } catch (Exception ex) {
            log.warn("WebSocket JWT validation failed: {}", ex.getMessage());
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
