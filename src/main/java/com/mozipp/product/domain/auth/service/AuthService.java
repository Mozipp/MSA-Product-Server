package com.mozipp.product.domain.auth.service;

import com.mozipp.product.global.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final WebClient webClient;
    private final JwtUtil jwtUtil;

    @Value("${auth.service.url}")
    private String authServiceUrl;

    public String test(String accessToken) {
        logger.info("test for print claims in username");
        Claims claims = jwtUtil.getClaimsFromToken(accessToken);

        return claims.getSubject();
    }
}
