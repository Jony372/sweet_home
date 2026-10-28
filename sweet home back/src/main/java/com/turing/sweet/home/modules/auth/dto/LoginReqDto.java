package com.turing.sweet.home.modules.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginReqDto(
        @NotBlank
        String username,
        @NotBlank
        String password,
        Boolean rememberMe
) {

}
