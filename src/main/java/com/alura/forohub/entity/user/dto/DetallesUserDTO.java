package com.alura.forohub.entity.user.dto;

import com.alura.forohub.entity.user.User;

public record DetallesUserDTO(
        Long id,
        String username,
        String firstname,
        String lastname,
        String country,
        Boolean enabled
) {
    public DetallesUserDTO(User usuario) {
        this(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getFirstname(),
                usuario.getLastname(),
                usuario.getCountry(),
                usuario.isEnabled()
        );

    }
}
