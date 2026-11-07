package com.apigateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * WebSocket Configuration for API Gateway
 * 
 * Spring Cloud Gateway uses reactive WebFlux for WebSocket support.
 * WebSocket routes are configured in application.yml with the following pattern:
 * - Path: /ws/** will be routed to ws://backend-service
 * - Path: /api/** will be routed to http://backend-service (HTTP)
 * 
 * The gateway automatically handles protocol upgrades from HTTP to WebSocket.
 * Authentication is handled by WebSocketAuthPreFilter which validates JWT tokens.
 * 
 * Spring Cloud Gateway already includes optimal Reactor-Netty configuration for
 * WebSocket connections, so no additional beans are required.
 */
@Configuration
@Slf4j
public class WebSocketConfig {
    // WebSocket routing is automatically handled by Spring Cloud Gateway
    // based on the route configuration in application.yml
}

