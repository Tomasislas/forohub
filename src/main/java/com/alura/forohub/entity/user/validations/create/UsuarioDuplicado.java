package com.alura.forohub.entity.user.validations.create;

import com.alura.forohub.entity.user.dto.CrearUserDTO;
import com.alura.forohub.entity.user.repository.UserRepo;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDuplicado implements ValidarCrearUsuario {
    @Autowired
    private UserRepo repo;

    @Override
    public void validate(CrearUserDTO data) {

        var usuarioDduplicado = repo.findByUsername(data.username());
        if (usuarioDduplicado != null) {
            throw new ValidationException("Usuario Existente");
        }
    }

}
