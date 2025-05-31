package com.projectmanagement.service.interfaces;

import com.projectmanagement.domain.model.GenericApiResponse;
import com.projectmanagement.service.DTO.SignUpRequestDto;


public interface IUserService   {

    public GenericApiResponse<String,String> login(String username, String password);
    public GenericApiResponse<String,String> signUp(SignUpRequestDto signUpRequest);
}