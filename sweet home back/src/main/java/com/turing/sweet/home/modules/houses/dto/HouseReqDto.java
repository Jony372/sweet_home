package com.turing.sweet.home.modules.houses.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

// DTO que recibe la peticion de creacion y edicion de una casa
public record HouseReqDto(
        @NotBlank
        String name,
        @NotBlank
        String description,
        @NotBlank
        String imgUrl,
        @NotBlank
        BigDecimal price,
        @NotBlank
        Long categoryId
) {
}
