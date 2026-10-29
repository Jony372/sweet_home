package com.turing.sweet.home.modules.ratings.dto;

import jakarta.validation.constraints.Min;

public record RatingReqDto(
        Long houseId,
        @Min(value = 1, message = "La calificación debe ser minimo de 1")
        @Min(value = 5, message = "La calificación debe ser máximo de 5")
        int rating,
        String comment
) {
}
