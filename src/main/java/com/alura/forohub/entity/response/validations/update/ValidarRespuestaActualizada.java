package com.alura.forohub.entity.response.validations.update;

import com.alura.forohub.entity.response.dto.ActualizarRespuestaDTO;

public interface ValidarRespuestaActualizada {
    void validate(ActualizarRespuestaDTO data, Long respuestaId);

}
