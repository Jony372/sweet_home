package com.turing.sweet.home.modules.users.service;

import com.turing.sweet.home.modules.users.dto.AdminUserViewDto;
import com.turing.sweet.home.modules.users.dto.ChangePasswordDto;
import com.turing.sweet.home.modules.users.dto.UpdateProfileDto;
import com.turing.sweet.home.modules.users.dto.UserProfileDto;
import org.springframework.data.domain.Page;

public interface UserServiceInterface {
    Page<AdminUserViewDto> getAllUsers(int page, int elements, String sortBy, String sortDirection);
    UserProfileDto getUserProfileById(Long userId);
    UserProfileDto changeUserPassword(Long userId, ChangePasswordDto changePasswordDto);
    UserProfileDto updateUserProfile(Long userId, UpdateProfileDto updateProfileDto);
    void makeUserAdmin(Long userId);
    void deleteUser(Long userId);
}
