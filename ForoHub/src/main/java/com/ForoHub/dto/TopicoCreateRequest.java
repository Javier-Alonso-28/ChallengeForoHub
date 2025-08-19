package com.ForoHub.dto;

public record TopicoCreateRequest(
        @jakarta.validation.constraints.NotBlank String titulo,
        @jakarta.validation.constraints.NotBlank String mensaje,
        @jakarta.validation.constraints.NotBlank String autor,
        @jakarta.validation.constraints.NotBlank String curso
) {}


