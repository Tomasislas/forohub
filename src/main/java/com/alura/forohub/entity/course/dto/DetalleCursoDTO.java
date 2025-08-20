package com.alura.forohub.entity.course.dto;

import com.alura.forohub.entity.course.Category;
import com.alura.forohub.entity.course.Course;

public record DetalleCursoDTO(
        Long id,
        String name,
        Category category,
        Boolean active
) {
    public DetalleCursoDTO(Course course){
        this(
                course.getId(),
                course.getName(),
                course.getCategory(),
                course.getActive()
        );
    }
}
