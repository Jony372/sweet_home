package com.turing.sweet.home.modules.auth.dto;

import com.turing.sweet.home.modules.users.dto.UserProfileDto;

public record LoginResDto(
        String token,
        UserProfileDto user
) {
}
