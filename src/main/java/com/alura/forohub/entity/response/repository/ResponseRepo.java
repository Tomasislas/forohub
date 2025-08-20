package com.alura.forohub.entity.response.repository;

import com.alura.forohub.entity.response.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepo extends JpaRepository<Response, Long> {
    Page<Response> findAllByTopicoId(Long topicoId, Pageable pageable);

    Page<Response> findAllByUsuarioId(Long usuarioId, Pageable pageable);

    Response getReferenceByTopicoId(Long id);

    Response getReferenceById(Long id);
}
