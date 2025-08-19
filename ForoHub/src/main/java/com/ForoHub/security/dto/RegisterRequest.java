package com.ForoHub.security.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank @Size(min = 3, max = 100) String username,
        @NotBlank @Size(min = 8, max = 64) String password,
        @NotBlank @Pattern(regexp = "USER|ADMIN", message = "role debe ser USER o ADMIN") String role
) {}
