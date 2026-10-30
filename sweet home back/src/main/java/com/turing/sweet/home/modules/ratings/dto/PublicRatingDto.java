package com.turing.sweet.home.modules.ratings.dto;

public record PublicRatingDto(
        Long id,
        String username,
        String houseName,
        String houseImgUrl,
        int rating,
        String comment
) {
}
