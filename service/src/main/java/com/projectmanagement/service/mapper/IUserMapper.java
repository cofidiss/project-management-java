package com.projectmanagement.service.mapper;

import com.projectmanagement.domain.util.CustomUtil;
import com.projectmanagement.service.DTO.SignUpRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.projectmanagement.domain.entity.UserEntity;

@Mapper(componentModel = "spring",uses = CustomUtil.class)
public interface IUserMapper {
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password", qualifiedByName = "hashPassword")
    UserEntity toEntity(SignUpRequestDto dto);

}
