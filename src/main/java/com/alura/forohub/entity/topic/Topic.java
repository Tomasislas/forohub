package com.alura.forohub.entity.topic;

import com.alura.forohub.entity.course.Course;
import com.alura.forohub.entity.topic.dto.ActualizarTopicoDTO;
import com.alura.forohub.entity.topic.dto.CrearTopicoDTO;
import com.alura.forohub.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@SuppressWarnings("ALL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "topicos")
@Entity(name = "topico")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "ultima_actualizacion")
    private LocalDateTime ultimaActualizacion;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private User usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Course curso;

    public Topic(CrearTopicoDTO crearTopicoDTO, User usuario, Course curso ) {
        this.titulo = crearTopicoDTO.titulo();
        this.mensaje = crearTopicoDTO.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.ultimaActualizacion = LocalDateTime.now();
        this.estado = Estado.OPEN;
        this.usuario = usuario;
        this.curso = curso;
    }

    public void actualizarTopicoConCurso(ActualizarTopicoDTO actualizarTopicoDTO, Course curso) {
        if (actualizarTopicoDTO.titulo() != null) {
            this.titulo = actualizarTopicoDTO.titulo();
        }
        if (actualizarTopicoDTO.mensaje() != null) {
            this.mensaje = actualizarTopicoDTO.mensaje();
        }
        if (actualizarTopicoDTO.estado() != null) {
            this.estado = actualizarTopicoDTO.estado();
        }
        if (actualizarTopicoDTO.cursoId() != null) {
            this.curso = curso;
        }
        this.ultimaActualizacion = LocalDateTime.now();
    }

    public void actualizarTopico(ActualizarTopicoDTO actualizarTopicoDTO) {
        if (actualizarTopicoDTO.titulo() != null) {
            this.titulo = actualizarTopicoDTO.titulo();
        }
        if (actualizarTopicoDTO.mensaje() != null) {
            this.mensaje = actualizarTopicoDTO.mensaje();
        }
        if (actualizarTopicoDTO.estado() != null) {
            this.estado = actualizarTopicoDTO.estado();
        }
        this.ultimaActualizacion = LocalDateTime.now();
    }

    public void eliminarTopico(){
        this.estado = Estado.DELETED;
    }
}
