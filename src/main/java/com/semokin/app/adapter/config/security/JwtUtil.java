package com.semokin.app.adapter.config.security;

import com.semokin.app.domain.model.AppUser;
import com.semokin.app.domain.model.Role;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtUtil {
    @Value("${app.loan.jwt.jwt-secret}")
    private String jwtSecret;
    @Value("${app.loan.jwt.app-name}")
    private String appName;
    @Value("${app.loan.jwt.expired}")
    private long jwtExpired;

    private Algorithm algorithm;

    @PostConstruct
    private void initAlgorithm() {
        this.algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(AppUser appUser) {
        var jwt = JWT.create();
        for (Role role : appUser.getRoles()) {
            jwt.withClaim("role", role.getRole().name());
        }
        return jwt // bikin token jwt
                .withIssuer(appName)
                .withSubject(appUser.getId())
                .withExpiresAt(Instant.now().plusSeconds(jwtExpired))
                .withIssuedAt(Instant.now())
//                .withClaim("role", appUser.getRoles().stream().toList())
                .sign(algorithm);
    }

    public boolean verifyJwtToken(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getIssuer().equals(appName);
    }

    public Map<String, String> getUserInfoByToken(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);

        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("userId", decodedJWT.getSubject());
        userInfo.put("role", decodedJWT.getClaim("role").asString());
        return userInfo;
    }
}
