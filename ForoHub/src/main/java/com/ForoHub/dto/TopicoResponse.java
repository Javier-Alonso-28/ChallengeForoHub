package com.ForoHub.dto;

public record TopicoResponse(
        Long id,
        String titulo,
        String mensaje,
        java.time.LocalDateTime fechaCreacion,
        String status,
        String autor,
        String curso
) {
}
