package com.turing.sweet.home.modules.users.dto;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordDto(
        @NotBlank(message = "La contraseña actual no puede estar vacía")
        String oldPassword,
        @NotBlank(message = "La nueva contraseña no puede estar vacía")
        String newPassword
) {
}
