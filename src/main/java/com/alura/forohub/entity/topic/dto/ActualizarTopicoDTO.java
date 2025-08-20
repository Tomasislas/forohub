package com.alura.forohub.entity.topic.dto;

import com.alura.forohub.entity.topic.Estado;

public record ActualizarTopicoDTO(
        String titulo,
        String mensaje,
        Estado estado,
        Long cursoId
) {
}
