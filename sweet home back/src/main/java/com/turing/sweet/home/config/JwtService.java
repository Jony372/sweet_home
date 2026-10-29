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

    /**
     * Genera un token JWT para un usuario dado.
     *
     * @param user El usuario para el cual se generará el token.
     * @param rememberMe Indica si el token debe durar más tiempo (30 días) o menos (1 día).
     * @return El token JWT generado.
     */
    public String generateToken(UserEntity user, Boolean rememberMe) {
        // Tiempo en días que durará el token dependiendo si el usuario seleccionó "recordarme" o no
        var time = rememberMe ? 30:1;
        try {
            // Crear el algoritmo de firma con la clave secreta
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    // Configurar los claims del token
                    .withIssuer("SweetHome")
                    // Configurar el subject del token con el nombre de usuario
                    .withSubject(user.getUsername())
                    // Configurar el claim "admin" con el valor del atributo admin del usuario
                    .withClaim("admin", user.getAdmin())
                    .withIssuedAt(Instant.now())
                    // Configurar la fecha de expiración del token sumando el tiempo configurado al instante actual
                    .withExpiresAt(Instant.now().plus(time, ChronoUnit.DAYS))
                    .sign(algorithm);

        }catch(JWTCreationException exception){
            throw new RuntimeException("Error al generar el token JWT", exception);
        }
    }

    /**
     * Valida un token JWT y devuelve el nombre de usuario si es válido.
     *
     * @param token El token JWT a validar.
     * @return El nombre de usuario si el token es válido, null en caso contrario.
     */
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
