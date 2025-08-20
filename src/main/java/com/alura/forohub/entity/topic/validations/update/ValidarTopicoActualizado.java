package com.alura.forohub.entity.topic.validations.update;

import com.alura.forohub.entity.topic.dto.ActualizarTopicoDTO;
import org.springframework.stereotype.Component;

@Component
public interface ValidarTopicoActualizado {
    void validate(ActualizarTopicoDTO data);

}
