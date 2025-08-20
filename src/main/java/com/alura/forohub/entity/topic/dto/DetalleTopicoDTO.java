package com.alura.forohub.entity.topic.dto;

import com.alura.forohub.entity.course.Category;
import com.alura.forohub.entity.topic.Estado;
import com.alura.forohub.entity.topic.Topic;

import java.time.LocalDateTime;

public record DetalleTopicoDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        LocalDateTime ultimaActualizacion,
        Estado estado,
        String usuario,
        String curso,
        Category categoriaCurso
) {
    public DetalleTopicoDTO(Topic topico) {
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getUltimaActualizacion(),
                topico.getEstado(),
                topico.getUsuario().getUsername(),
                topico.getCurso().getName(),
                topico.getCurso().getCategory()
        );
    }
}
