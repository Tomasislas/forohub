package com.alura.forohub.entity.user.validations.update;

import com.alura.forohub.entity.user.dto.ActualizarUserDTO;
import org.springframework.stereotype.Component;

@Component
public interface IValidarActualizarUsuario {
    void validate(ActualizarUserDTO data);

}
