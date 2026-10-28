package com.turing.sweet.home.modules.users.dto;

import java.time.LocalDateTime;

public record PublicUserDto(
        String username,
        String name,
        String lastName,
        LocalDateTime createdAt
) {
}
