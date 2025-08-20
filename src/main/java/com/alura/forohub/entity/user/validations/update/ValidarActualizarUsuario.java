package com.alura.forohub.entity.user.validations.update;

import com.alura.forohub.entity.user.dto.ActualizarUserDTO;
import com.alura.forohub.entity.user.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarActualizarUsuario implements IValidarActualizarUsuario {
    @Autowired
    private UserRepo repo;

    public void validate(ActualizarUserDTO data) {

    }
}
