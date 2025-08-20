package com.alura.forohub.entity.topic.repository;

import com.alura.forohub.entity.topic.Estado;
import com.alura.forohub.entity.topic.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepo extends JpaRepository<Topic, Long> {
    Page<Topic> findAllByEstadoIsNot(Estado estado, Pageable pageable);

    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    Topic findByTitulo(String titulo);

    boolean existsByTitulo(String titulo);

    boolean existsById(Long id);
}
