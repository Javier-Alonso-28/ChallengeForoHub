package com.ForoHub.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {
    private final Algorithm algorithm;
    private final long expirationMinutes;

    public JwtService(
            @Value("${app.security.jwt.secret}") String secret,
            @Value("${app.security.jwt.expiration-minutes}") long expirationMinutes) {
        this.algorithm = Algorithm.HMAC256(secret);
        this.expirationMinutes = expirationMinutes;
    }

    public String generate(String subject, Map<String, Object> claims) {
        Instant now = Instant.now();
        Instant exp = now.plusSeconds(expirationMinutes * 60);

        var builder = JWT.create()
                .withSubject(subject)
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(exp));

        if (claims != null) {
            claims.forEach((k, v) -> {
                if (v instanceof Boolean b) builder.withClaim(k, b);
                else if (v instanceof Integer i) builder.withClaim(k, i);
                else if (v instanceof Long l) builder.withClaim(k, l);
                else if (v instanceof Double d) builder.withClaim(k, d);
                else if (v != null) builder.withClaim(k, v.toString());
                else builder.withClaim(k, (String) null);
            });
        }

        return builder.sign(algorithm);
    }

    public DecodedJWT verify(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }

    public Instant getExpiration(String token) {
        return verify(token).getExpiresAt().toInstant();
    }
}
