package com.projectmanagement.service.implementation;

import com.enums.RoleEnum;
import com.projectmanagement.domain.entity.UserEntity;
import com.projectmanagement.domain.model.GenericApiResponse;
import com.projectmanagement.domain.util.CustomUtil;
import com.projectmanagement.repository.interfaces.IUserRepository;
import com.projectmanagement.service.DTO.SignUpRequestDto;
import com.projectmanagement.service.interfaces.IUserService;
import com.projectmanagement.service.mapper.IUserMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
@Service
public class UserService implements IUserService {
    private IUserMapper userMapper;
    private IUserRepository userRepository;
private  JwtUtil jwtUtil;
    public UserService(IUserMapper userMapper, IUserRepository userRepository, JwtUtil jwtUtil) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public GenericApiResponse<String, String> login(String username, String password) {
        GenericApiResponse<String, String> apiResponse = new GenericApiResponse<>();
        Optional<UserEntity> userOpt = userRepository.findByUsername(username)
                .filter(user -> verifyPassword(password, user.getPassword()));

      if(userOpt.isPresent()){
          apiResponse.setSuccessData(jwtUtil.generateToken( userOpt.get().getId()));
      }
      else {
          apiResponse.setErrorData("login unsuccess");
      }
        return apiResponse; // Şifre doğrulaması

    }

    private boolean verifyPassword(String rawpassword, String encodedPassword) {
        return Objects.equals(CustomUtil.hashPassword(rawpassword), encodedPassword);
    }

    @Override
    public GenericApiResponse<String, String> signUp(SignUpRequestDto signUpRequest) {
        GenericApiResponse<String, String> apiResponse = new GenericApiResponse<>();

        // DTO'yu Entity'ye dönüştür
        UserEntity userEntityToInsert = userMapper.toEntity(signUpRequest);

        // Veritabanına ekle
        userRepository.save(userEntityToInsert);

        Optional<UserEntity> userOpt = userRepository.findByUsername(signUpRequest.getUsername());

        if(userOpt.isPresent()){
            apiResponse.setSuccessData(userOpt.get().getId().toString());
        }
        return apiResponse;
    }

    @Override
    public boolean hasPermission(Long userId, RoleEnum required) {
       var optUser =  userRepository.findById(userId);
        return  optUser.filter(user -> (user.getRoleId()  & required.getBit() ) !=0).isPresent();

    }
}
