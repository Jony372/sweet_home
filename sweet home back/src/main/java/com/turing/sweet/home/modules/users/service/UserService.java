package com.turing.sweet.home.modules.users.service;

import com.turing.sweet.home.exceptions.BadRequestException;
import com.turing.sweet.home.exceptions.ResourceNotFoundException;
import com.turing.sweet.home.modules.users.dto.AdminUserViewDto;
import com.turing.sweet.home.modules.users.dto.ChangePasswordDto;
import com.turing.sweet.home.modules.users.dto.UpdateProfileDto;
import com.turing.sweet.home.modules.users.dto.UserProfileDto;
import com.turing.sweet.home.modules.users.entity.UserEntity;
import com.turing.sweet.home.modules.users.mapper.UserMapper;
import com.turing.sweet.home.modules.users.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity getUserEntityById(Long userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario no fue encontrado"));
    }

    @Override
    public Page<AdminUserViewDto> getAllUsers(int page, int elements, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, elements, sort);
        return this.userRepository.findAllByDeletedAtIsNull(pageable)
                .map(UserMapper::toAdminUserViewDto);
    }

    @Override
    public UserProfileDto getUserProfileById(Long userId) {
        return UserMapper.toUserProfileDto(getUserEntityById(userId));
    }

    @Transactional
    @Override
    public UserProfileDto changeUserPassword(Long userId, ChangePasswordDto changePasswordDto) {
        UserEntity userEntity = getUserEntityById(userId);
        String currentPassword = userEntity.getPassword();
        if(!passwordEncoder.matches(changePasswordDto.oldPassword(), currentPassword)) {
            throw new BadRequestException("La contraseña actual es incorrecta");
        }
        userEntity.setPassword(passwordEncoder.encode(changePasswordDto.newPassword()));
        return UserMapper.toUserProfileDto(this.userRepository.save(userEntity));
    }

    @Transactional
    @Override
    public UserProfileDto updateUserProfile(Long userId, UpdateProfileDto updateProfileDto) {
        UserEntity userEntity = getUserEntityById(userId);
        userEntity.setName(updateProfileDto.name());
        userEntity.setLastName(updateProfileDto.lastName());
        userEntity.setEmail(updateProfileDto.email());
        userEntity.setPhone(updateProfileDto.phone());
        return UserMapper.toUserProfileDto(this.userRepository.save(userEntity));
    }

    @Transactional
    @Override
    public void makeUserAdmin(Long userId) {
        UserEntity userEntity = getUserEntityById(userId);
        userEntity.setAdmin(true);
        this.userRepository.save(userEntity);
    }

    @Transactional
    @Override
    public void deleteUser(Long userId) {
        UserEntity userEntity = getUserEntityById(userId);
        userEntity.delete();
        this.userRepository.save(userEntity);
    }
}
