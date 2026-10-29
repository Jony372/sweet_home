package com.turing.sweet.home.modules.ratings.dto;

import java.time.LocalDateTime;

public record PublicRatingsByHouseDto(
        Long id,
        String username,
        int rating,
        String comment,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
