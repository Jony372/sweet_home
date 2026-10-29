package com.turing.sweet.home.modules.houses.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PublicHouseDto(
        Long id,
        String name,
        String description,
        String imgUrl,
        BigDecimal price,
        String category,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
