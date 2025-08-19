package com.ForoHub.dto;

public record TopicoUpdateRequest(
        @jakarta.validation.constraints.NotBlank String titulo,
        @jakarta.validation.constraints.NotBlank String mensaje,
        @jakarta.validation.constraints.NotBlank String status
) {}
