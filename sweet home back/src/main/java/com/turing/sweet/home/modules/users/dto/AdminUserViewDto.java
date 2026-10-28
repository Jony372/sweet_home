package com.turing.sweet.home.modules.users.dto;

import java.time.LocalDateTime;

public record AdminUserViewDto(
        Long id,
        String username,
        String name,
        String lastName,
        String email,
        String phone,
        Boolean isAdmin,
        LocalDateTime createdAt,
        LocalDateTime deletedAt
) {
}
