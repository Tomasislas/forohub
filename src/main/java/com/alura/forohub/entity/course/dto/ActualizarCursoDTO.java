package com.alura.forohub.entity.course.dto;

import com.alura.forohub.entity.course.Category;

public record ActualizarCursoDTO(
        String name,
        Category category,
        Boolean active
) {
}
