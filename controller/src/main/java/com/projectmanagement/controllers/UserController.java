package com.projectmanagement.controllers;

import com.projectmanagement.domain.enums.RoleEnum;
import com.projectmanagement.controller.annotaiton.RequirePermission;
import com.projectmanagement.service.DTO.LoginRequestDto;
import com.projectmanagement.service.DTO.SignUpRequestDto;
import com.projectmanagement.service.implementation.JwtUtil;
import com.projectmanagement.service.interfaces.IUserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
public class UserController {

    private final IUserService userService;
    private final JwtUtil jwtUtil;

    public UserController(IUserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequestDto signUpRequest, HttpServletResponse response) {
        var result = userService.signUp(signUpRequest);
        if (result.isSuccess()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE,result.getSuccessData().toString())
                    .body(result.getSuccessData());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    @RequirePermission(RoleEnum.MODIFY)
    @PostMapping("/test")
    public String test( HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return  "";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        var result = userService.login(loginRequestDto.getUsername(), loginRequestDto.getPassword());
        if (result.isSuccess()) {

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE,result.getSuccessData().toString())
                    .build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    @GetMapping("/isLoggedIn")
    public boolean isLoggedIn(HttpServletResponse response) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null
                && auth.isAuthenticated()
                && !(auth instanceof AnonymousAuthenticationToken);
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            auth.setAuthenticated(false);
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtUtil.deleteCookie().toString())
                .build();
    }
    @GetMapping("/getUserRole")
    public ResponseEntity<?>  getUserRole(HttpServletResponse response) {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth.getPrincipal() instanceof Long userId ){
            var result = userService.getUserRole(userId);
            return ResponseEntity.status(result.getStatusCode()).body(result.getSuccessData());
        }
        else {
           return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
