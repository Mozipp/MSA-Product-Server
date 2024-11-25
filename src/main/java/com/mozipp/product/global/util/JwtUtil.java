package com.mozipp.product.global.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.KeyPair;

@Component
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${jwt.issuer}")
    private String issuer;

    @Getter
    @Value("${jwt.access-token.expiration}")
    private long accessTokenExpiration;

    @Getter
    @Value("${jwt.refresh-token.expiration}")
    private long refreshTokenExpiration;

    @Value("${jwt.keystore.path}")
    private String keystorePath;

    @Value("${jwt.keystore.password}")
    private String keystorePassword;

    @Value("${jwt.key.alias}")
    private String keyAlias;

    @Value("${jwt.key.password}")
    private String keyPassword;

    @Getter
    private KeyPair keyPair;

//    @PostConstruct
//    public void init() throws Exception {
//        logger.info("Initializing JWT Util...");
//        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
//        try (InputStream keyStoreStream = getClass().getClassLoader().getResourceAsStream(keystorePath)) {
//            if (keyStoreStream == null) {
//                throw new IllegalArgumentException("키스토어 파일을 찾을 수 없습니다: " + keystorePath);
//            }
//            keyStore.load(keyStoreStream, keystorePassword.toCharArray());
//        }
//        KeyStore.PasswordProtection keyPasswordProtection = new KeyStore.PasswordProtection(keyPassword.toCharArray());
//        KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(keyAlias, keyPasswordProtection);
//        keyPair = new KeyPair(privateKeyEntry.getCertificate().getPublicKey(), privateKeyEntry.getPrivateKey());
//        logger.info("JWT Util initialized successfully with key alias: {}", keyAlias);
//    }

    public Claims getClaimsFromToken(String token) {
        logger.info("Extracting claims from token...");
        return Jwts.parserBuilder()
                .setSigningKey(keyPair.getPublic())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

