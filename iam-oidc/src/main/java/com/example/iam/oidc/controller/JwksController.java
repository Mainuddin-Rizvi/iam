package com.example.iam.oidc.controller;

import com.example.iam.auth.jwt.JwkProvider;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.util.JSONObjectUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/** Serves JWKS for OIDC discovery */
@RestController
@RequestMapping("/oidc")
public class JwksController {

    private final JwkProvider jwkProvider;

    public JwksController(JwkProvider jwkProvider) {
        this.jwkProvider = jwkProvider;
    }

    @GetMapping("/.well-known/jwks.json")
    public ResponseEntity<String> jwks() {
        JWKSet full = jwkProvider.getJwks();
        JWKSet publicOnly = full.toPublicJWKSet();
        String json = JSONObjectUtils.toJSONString(publicOnly.toJSONObject());
        return ResponseEntity.ok(json);
    }

    @GetMapping("/.well-known/openid-configuration")
    public ResponseEntity<String> discovery() {
        String json = "{ \"issuer\":\"http://localhost:8080\", " +
                "\"jwks_uri\":\"http://localhost:8080/oidc/.well-known/jwks.json\" }";
        return ResponseEntity.ok(json);
    }
}
