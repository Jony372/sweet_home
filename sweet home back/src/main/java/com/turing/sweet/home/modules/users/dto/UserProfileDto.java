package com.turing.sweet.home.modules.users.dto;

import java.time.LocalDateTime;

// DTO para la vista del usuario desde su perfil
public record UserProfileDto(
        String username,
        String name,
        String lastName,
        String email,
        String phone,
        Boolean isAdmin,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
