package com.alura.forohub.entity.user.dto;

public record ActualizarUserDTO(
        String firstname,
        String lastname,
        String country,
        Boolean enabled
) {
}
