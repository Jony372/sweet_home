package com.turing.sweet.home.modules.users.mapper;

import com.turing.sweet.home.modules.users.dto.AdminUserViewDto;
import com.turing.sweet.home.modules.users.dto.UserProfileDto;
import com.turing.sweet.home.modules.users.entity.UserEntity;

public class UserMapper {
    public static UserProfileDto toUserProfileDto(UserEntity userEntity) {
        return new UserProfileDto(
                userEntity.getUsername(),
                userEntity.getName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getPhone(),
                userEntity.getAdmin(),
                userEntity.getCreatedAt(),
                userEntity.getUpdatedAt()
        );
    }

    public static AdminUserViewDto toAdminUserViewDto(UserEntity userEntity) {
        return new AdminUserViewDto(
                userEntity.getUserId(),
                userEntity.getUsername(),
                userEntity.getName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getPhone(),
                userEntity.getAdmin(),
                userEntity.getCreatedAt(),
                userEntity.getDeletedAt()
        );
    }
}
