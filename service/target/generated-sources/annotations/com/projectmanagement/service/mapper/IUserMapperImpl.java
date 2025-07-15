package com.projectmanagement.service.mapper;

import com.projectmanagement.domain.entity.UserEntity;
import com.projectmanagement.domain.util.CustomUtil;
import com.projectmanagement.service.DTO.SignUpRequestDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-15T17:49:35+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (JetBrains s.r.o.)"
)
@Component
public class IUserMapperImpl implements IUserMapper {

    @Override
    public UserEntity toEntity(SignUpRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUsername( dto.getUsername() );
        userEntity.setPassword( CustomUtil.hashPassword( dto.getPassword() ) );
        if ( dto.getRoleId() != null ) {
            userEntity.setRoleId( dto.getRoleId().intValue() );
        }

        return userEntity;
    }
}
