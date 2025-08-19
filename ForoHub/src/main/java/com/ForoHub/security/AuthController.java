package com.ForoHub.security;

import com.ForoHub.security.dto.AuthRequest;
import com.ForoHub.security.dto.AuthResponse;
import com.ForoHub.security.dto.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/registro")
    public ResponseEntity<Void> registro(@RequestBody @Valid RegisterRequest req) {
        service.register(req);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest req) {
        return ResponseEntity.ok(service.login(req));
    }
}
