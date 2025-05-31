package com.projectmanagement.service.implementation;

import com.projectmanagement.repository.interfaces.IUserRepository;
import com.projectmanagement.service.config.CustomUserDetail;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService {
    IUserRepository userRepository;
public CustomUserDetailsService(IUserRepository userRepository){


this.userRepository = userRepository;

}


    public CustomUserDetail loadUserByUserId(long userId) {
        var optUser =  userRepository.findById( userId);

        return new CustomUserDetail(optUser);
    }
}