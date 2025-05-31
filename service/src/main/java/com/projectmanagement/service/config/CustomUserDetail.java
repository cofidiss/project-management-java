package com.projectmanagement.service.config;

import com.enums.RoleEnum;
import com.projectmanagement.domain.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomUserDetail  {

    private Optional<UserEntity> userEntity;

    public CustomUserDetail(Optional<UserEntity> userEntity) {

        this.userEntity = userEntity;
    }

    public Long getUserId() {
        return userEntity.isPresent() ? userEntity.get().getId() : null;
    }


    public String getUsername() {
        return userEntity.isPresent() ? userEntity.get().getUsername() : null;
    }

    public Collection<GrantedAuthority> getAuthorities() {
        if (userEntity.isEmpty()) return List.of();

        var roleMask = userEntity.get().getRoleId(); // örn: 5 → 0101 → READ + UPDATE

        List<RoleEnum> roles = RoleEnum.fromMask(roleMask);
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority( role.name()))
                .collect(Collectors.toList());
    }
}
