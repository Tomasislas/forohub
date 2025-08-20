package com.alura.forohub.controller;

import com.alura.forohub.entity.course.Course;
import com.alura.forohub.entity.course.repository.CourseRepository;
import com.alura.forohub.entity.response.dto.DetalleRespuestaDTO;
import com.alura.forohub.entity.response.repository.ResponseRepo;
import com.alura.forohub.entity.topic.Estado;
import com.alura.forohub.entity.topic.Topic;
import com.alura.forohub.entity.topic.dto.ActualizarTopicoDTO;
import com.alura.forohub.entity.topic.dto.CrearTopicoDTO;
import com.alura.forohub.entity.topic.dto.DetalleTopicoDTO;
import com.alura.forohub.entity.topic.repository.TopicRepo;
import com.alura.forohub.entity.topic.validations.create.ValidarTopicoCreado;
import com.alura.forohub.entity.topic.validations.update.ValidarTopicoActualizado;
import com.alura.forohub.entity.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/api/v1/topicos")
@RequiredArgsConstructor
public class TopicoController {

    private final TopicRepo topicRepo;
    private final CourseRepository cursoRepo;
    private final ResponseRepo respuestaRepo;
    private final List<ValidarTopicoCreado> crearValidadores;
    private final List<ValidarTopicoActualizado> actualizarValidadores;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalleTopicoDTO> crearTopico(@RequestBody @Valid CrearTopicoDTO crearTopicoDTO,
                                                        UriComponentsBuilder uriBuilder,
                                                        @AuthenticationPrincipal User usuario) { // Forma segura de obtener el usuario
        crearValidadores.forEach(v -> v.validate(crearTopicoDTO));

        Course curso = cursoRepo.findById(crearTopicoDTO.cursoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso no encontrado"));

        Topic topico = new Topic(crearTopicoDTO, usuario, curso);
        topicRepo.save(topico);

        var uri = uriBuilder.path("/api/v1/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalleTopicoDTO(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DetalleTopicoDTO>> leerTopicosExistentes(
            @PageableDefault(size = 5, sort = {"ultimaActualizacion"}, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Topic> topicos = topicRepo.findAllByEstadoIsNot(Estado.DELETED, pageable);
        return ResponseEntity.ok(topicos.map(DetalleTopicoDTO::new));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<DetalleTopicoDTO>> leerTodosLosTopicos(
            @PageableDefault(size = 5, sort = {"ultimaActualizacion"}, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Topic> topicos = topicRepo.findAll(pageable);
        return ResponseEntity.ok(topicos.map(DetalleTopicoDTO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleTopicoDTO> leerUnTopico(@PathVariable Long id) {
        Topic topico = topicRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado"));
        return ResponseEntity.ok(new DetalleTopicoDTO(topico));
    }

    @GetMapping("/{id}/respuestas")
    public ResponseEntity<Page<DetalleRespuestaDTO>> leerRespuestasDeTopico(@PathVariable Long id,
                                                                            @PageableDefault(size = 5) Pageable pageable) {
        // En tu repositorio de respuestas, tendrías que crear un método para
        // encontrar todas las respuestas de un tópico específico.
        // Ejemplo: Page<Response> respuestas = respuestaRepo.findAllByTopicoId(id, pageable);
        // Page<Response> respuestas = ... tu lógica aquí ...
        // return ResponseEntity.ok(respuestas.map(DetalleRespuestaDTO::new));

        // Por ahora, para que compile, devolvemos una respuesta de error.
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Funcionalidad aún no implementada");
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalleTopicoDTO> actualizarTopico(@RequestBody @Valid ActualizarTopicoDTO actualizarTopicoDTO,
                                                             @PathVariable Long id) {
        Topic topico = topicRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado"));

        actualizarValidadores.forEach(v -> v.validate(actualizarTopicoDTO));

        if (actualizarTopicoDTO.cursoId() != null) {
            Course curso = cursoRepo.findById(actualizarTopicoDTO.cursoId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso no encontrado"));
            topico.actualizarTopicoConCurso(actualizarTopicoDTO, curso);
        } else {
            topico.actualizarTopico(actualizarTopicoDTO);
        }

        return ResponseEntity.ok(new DetalleTopicoDTO(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        Topic topico = topicRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado"));
        topico.eliminarTopico();
        return ResponseEntity.noContent().build();
    }
}
