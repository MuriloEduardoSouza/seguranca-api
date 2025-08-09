package com.murilo.segurancaAPI.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.murilo.segurancaAPI.entity.Role;
import com.murilo.segurancaAPI.entity.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.issuer:segurancaAPI}")
    private String issuer;

    @Value("${jwt.expiration:3600}")
    private Long expirationSeconds;

    private Algorithm algorithm(){
        return Algorithm.HMAC256(secret);
    }

    public String gerarToken(String login, Role role){
        Instant now = Instant.now();
        Instant exp = now.plusSeconds(expirationSeconds);

        return JWT.create()
                .withIssuer(issuer)
                .withSubject(login)
                .withClaim("role", role.name())
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(exp))
                .sign(algorithm());
    }

    public DecodedJWT validar(String token){
        JWTVerifier verifier = JWT.require(algorithm())
                .withIssuer(issuer)
                .acceptLeeway(2)
                .build();
        return verifier.verify(token);
    }

    public String getSubject(String token){
        return validar(token).getSubject();
    }

    public Long getExpirationSeconds(){
        return expirationSeconds;
    }
}
