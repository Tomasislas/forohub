package com.alura.forohub.entity.course.dto;

import com.alura.forohub.entity.course.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CrearCursoDTO(
        @NotBlank
        String name,
        @NotNull
        Category category
) {
}
