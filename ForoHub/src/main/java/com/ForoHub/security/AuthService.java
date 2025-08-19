package com.ForoHub.security;

import com.ForoHub.security.dto.AuthRequest;
import com.ForoHub.security.dto.AuthResponse;
import com.ForoHub.security.dto.RegisterRequest;
import com.ForoHub.security.jwt.JwtService;
import com.ForoHub.security.user.UserAccount;
import com.ForoHub.security.user.UserAccountRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

    private final UserAccountRepository repo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public AuthService(UserAccountRepository repo,
                       PasswordEncoder encoder,
                       AuthenticationManager authManager,
                       JwtService jwtService) {
        this.repo = repo;
        this.encoder = encoder;
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    public void register(RegisterRequest req) {
        if (repo.existsByUsername(req.username())) {
            throw new IllegalArgumentException("El usuario ya existe");
        }

        var u = new UserAccount();
        u.setUsername(req.username());
        u.setPassword(encoder.encode(req.password()));
        u.setRole(req.role().toUpperCase());
        repo.save(u);
    }

    public AuthResponse login(AuthRequest req) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.username(), req.password()));

        var user = (UserAccount) auth.getPrincipal();
        var token = jwtService.generate(user.getUsername(), Map.of("role", user.getRole()));
        return new AuthResponse(token, jwtService.getExpiration(token));
    }
}
