package com.ForoHub.domian;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "topicos",
        uniqueConstraints = @UniqueConstraint(name = "uk_topico", columnNames = {"titulo", "mensaje"}))
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String titulo;

    @Column(nullable = false, length = 2000)
    private String mensaje;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(nullable = false, length = 255)
    private String autor;

    @Column(nullable = false, length = 255)
    private String curso;

    @PrePersist
    void prePersist() {
        if (fechaCreacion == null) fechaCreacion = LocalDateTime.now();
        if (status == null) status = "ABIERTO";
    }
}
