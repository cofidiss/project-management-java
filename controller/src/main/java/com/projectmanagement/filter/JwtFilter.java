package com.projectmanagement.filter;

import com.projectmanagement.service.implementation.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final String USER_ID_HEADER = "X-User-Id";

    private final CustomUserDetailsService userDetailsService;

    @Value("${security.gateway.shared-secret}")
    private String gatewaySharedSecret;

    @Value("${security.gateway.shared-header:X-Gateway-Auth}")
    private String gatewaySharedHeader;

    public JwtFilter(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String trustedGatewaySecret = request.getHeader(gatewaySharedHeader);
        if (!Objects.equals(gatewaySharedSecret, trustedGatewaySecret)) {
            filterChain.doFilter(request, response);
            return;
        }

        String userIdHeaderValue = request.getHeader(USER_ID_HEADER);
        if (StringUtils.hasText(userIdHeaderValue) && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                Long userId = Long.parseLong(userIdHeaderValue);
                List<GrantedAuthority> authorities = userDetailsService
                        .loadUserByUserId(userId)
                        .getAuthorities()
                        .stream()
                        .map(a -> (GrantedAuthority) a)
                        .toList();

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userId,
                        null,
                        authorities
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            } catch (NumberFormatException ignored) {
                // Geçersiz header — unauthenticated bırak
            }
        }

        filterChain.doFilter(request, response);
    }
}
