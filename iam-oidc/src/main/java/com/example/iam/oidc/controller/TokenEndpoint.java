package com.example.iam.oidc.controller;

import com.example.iam.common.dto.TokenResponse;
import com.example.iam.core.service.OidcService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

/** OIDC token endpoint */
@RestController
@RequestMapping("/oidc")
public class TokenEndpoint {

    private final OidcService oidcService;

    public TokenEndpoint(OidcService oidcService) {
        this.oidcService = oidcService;
    }

    @PostMapping("/token")
    public ResponseEntity<TokenResponse> token(@RequestParam String grant_type,
                                               @RequestParam(required = false) String code,
                                               @RequestParam String client_id) {
        if ("authorization_code".equals(grant_type)) {
            return ResponseEntity.ok(oidcService.issueOidcTokens(code, client_id));
        }
        TokenResponse error = new TokenResponse();
        error.setAccessToken("unsupported_grant");
        error.setExpiresAt(Instant.now());
        return ResponseEntity.badRequest().body(error);
    }
}
