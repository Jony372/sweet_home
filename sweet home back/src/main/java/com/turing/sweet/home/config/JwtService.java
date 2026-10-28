package com.turing.sweet.home.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.turing.sweet.home.modules.users.entity.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class JwtService {
    @Value("${api.security.jwt.secret}")
    private String secret;

    public String generateToken(UserEntity user, Boolean rememberMe) {
        var time = rememberMe ? 30:1;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("SweetHome")
                    .withSubject(user.getUsername())
                    .withClaim("admin", user.getAdmin())
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(Instant.now().plus(time, ChronoUnit.DAYS))
                    .sign(algorithm);

        }catch(JWTCreationException exception){
            throw new RuntimeException("Error al generar el token JWT", exception);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("SweetHome")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch(JWTVerificationException exception){
            return null;
        }
    }
}
