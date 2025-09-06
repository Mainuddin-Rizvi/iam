package com.example.iam.auth.jwt;


import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/** Provides JWKS keys for OIDC */
@Component
public class JwkProvider {

    private final KeyPair keyPair;

    public JwkProvider() {
        this.keyPair = generateRsaKeyPair();
    }

    public JWKSet getJwks() {
        RSAKey jwk = new RSAKey.Builder((java.security.interfaces.RSAPublicKey) keyPair.getPublic())
                .privateKey(keyPair.getPrivate())
                .keyID("demo-key")
                .build();
        return new JWKSet(jwk);
    }

    public KeyPair generateRsaKeyPair() {
        try {
            KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
            gen.initialize(2048);
            return gen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }
}
