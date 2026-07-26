package com.projectmanagement.service.interfaces;

import com.projectmanagement.domain.enums.RoleEnum;
import com.projectmanagement.domain.model.GenericApiResponse;
import com.projectmanagement.service.DTO.SignUpRequestDto;
import org.springframework.http.ResponseCookie;


public interface IUserService   {

    public GenericApiResponse<String, ResponseCookie> login(String username, String password);
    public GenericApiResponse<String,ResponseCookie> signUp(SignUpRequestDto signUpRequest);

    boolean hasPermission(Long userId, RoleEnum required);

    GenericApiResponse<String, Long>  getUserRole(Long userId);
}