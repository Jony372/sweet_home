package com.turing.sweet.home.modules.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateProfileDto(
        @NotBlank(message = "El nombre de usuario no puede estar vacío")
        String username,
        @NotBlank(message = "El nombre no puede estar vacío")
        String name,
        @NotBlank(message = "El apellido no puede estar vacío")
        String lastName,
        @NotBlank(message = "El correo electrónico no puede estar vacío")
        @Email(message = "El correo electrónico no es válido")
        String email,
        @NotBlank(message = "El teléfono no puede estar vacío")
        String phone
) {
}
