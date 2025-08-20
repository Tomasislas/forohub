package com.alura.forohub.entity.course.repository;

import com.alura.forohub.entity.course.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Page<Course> findAllByActiveTrue(Pageable pageable);
}
