package com.alura.forohub.entity.user.dto;

import jakarta.validation.constraints.NotBlank;

public record CrearUserDTO(
        @NotBlank
        String username,
        @NotBlank
        String Password,
        @NotBlank
        String firstname,
        @NotBlank
        String lastname,
        @NotBlank
        String country
) {
}
