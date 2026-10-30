package com.turing.sweet.home.modules.ratings.dto;

import java.time.LocalDateTime;

// DTO para mostrar los ratings de una casa en la vista publica
public record PublicRatingsByHouseDto(
        Long id,
        String username,
        int rating,
        String comment,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
