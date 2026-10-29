package com.turing.sweet.home.modules.cateogories.dto;

import jakarta.validation.constraints.NotBlank;

// DTO como peticion para creacion y edicion de una categoria
public record CategoryReqDto(
        @NotBlank
        String name,
        @NotBlank
        String description
) {
}
