package com.ForoHub.security.dto;

import java.time.Instant;

public record AuthResponse(
        String token,
        Instant expiresAt
) {}
