package com.turing.sweet.home.modules.users.dto;

import java.time.LocalDateTime;

// DTO para representar la vista de un usuario en el panel de administración
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
