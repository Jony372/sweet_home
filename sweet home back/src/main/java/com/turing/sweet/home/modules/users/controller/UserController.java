package com.turing.sweet.home.modules.users.controller;

import com.turing.sweet.home.modules.users.dto.AdminUserViewDto;
import com.turing.sweet.home.modules.users.dto.UserProfileDto;
import com.turing.sweet.home.modules.users.entity.UserEntity;
import com.turing.sweet.home.modules.users.repository.UserRepository;
import com.turing.sweet.home.modules.users.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<AdminUserViewDto>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int elements,
            @RequestParam(defaultValue = "username") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection
    ) {
        Page<AdminUserViewDto> users = this.userService.getAllUsers(page, elements, sortBy, sortDirection);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/me/profile")
    public ResponseEntity<UserProfileDto> getMyProfile(
            @AuthenticationPrincipal UserEntity user
            ) {
        Long id = user.getUserId();
        return ResponseEntity.ok(this.userService.getUserProfileById(id));
    }
}
