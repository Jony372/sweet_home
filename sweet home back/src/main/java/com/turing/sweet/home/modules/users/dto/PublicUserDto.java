package com.turing.sweet.home.modules.users.dto;

import java.time.LocalDateTime;

// DTO de la vista publica de un usuario
public record PublicUserDto(
        String username,
        String name,
        String lastName,
        LocalDateTime createdAt
) {
}
