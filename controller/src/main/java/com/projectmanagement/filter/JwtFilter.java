package com.projectmanagement.filter;

import com.projectmanagement.service.config.CustomUserDetail;
import com.projectmanagement.service.implementation.CustomUserDetailsService;
import com.projectmanagement.service.implementation.JwtUtil;
import com.projectmanagement.service.implementation.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component

public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();
        String token = null;
        if (cookies != null) {
            token = Arrays.stream(cookies).filter(c -> UserService.COOKIE_NAME.equals(c.getName())).map(Cookie::getValue).findFirst().orElse(null);
        }
        if (token != null) {
            Long userId = jwtUtil.extractUserId(token);

            if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null && !jwtUtil.isTokenExpired(token)) {
                CustomUserDetail userDetails = userDetailsService.loadUserByUserId(userId);
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userId, null, userDetails.getAuthorities());

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);

            }
        }

        filterChain.doFilter(request, response);
    }
}
