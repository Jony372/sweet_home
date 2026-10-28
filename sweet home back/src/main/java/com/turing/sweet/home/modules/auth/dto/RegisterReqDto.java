package com.turing.sweet.home.modules.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterReqDto(
        @NotBlank(message = "El nombre de usuario no puede estar vacío")
        String username,
        @NotBlank(message = "El nombre no puede estar vacío")
        String name,
        String lastName,
        @Email(message = "El correo electrónico no es válido")
        String email,
        String phone,
        @NotBlank(message = "La contraseña no puede estar vacía")
        String password
) {
}
