package com.projectmanagement.controller;

import com.projectmanagement.service.DTO.LoginRequestDto;
import com.projectmanagement.service.DTO.SignUpRequestDto;
import com.projectmanagement.service.interfaces.IUserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public String signUp(@RequestBody SignUpRequestDto signUpRequest, HttpServletResponse response) {
        var result = userService.signUp(signUpRequest);
        if (result.isSuccess()) {

            return "Signup Successful";
        }
        return "Signup Unsuccessful";
    }

    @PostMapping("/test")
    public String test( HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return  "";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        var result = userService.login(loginRequestDto.getUsername(), loginRequestDto.getPassword());
        if (result.isSuccess()) {

            return ResponseEntity.ok(result.getSuccessData());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
