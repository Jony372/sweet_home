package com.turing.sweet.home.modules.users.dto;

import java.time.LocalDateTime;

public record UserProfileDto(
        String username,
        String name,
        String lastName,
        String email,
        String phone,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
