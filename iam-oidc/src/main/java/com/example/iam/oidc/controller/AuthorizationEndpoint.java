package com.example.iam.oidc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** Handles OIDC authorize endpoint */
@RestController
@RequestMapping("/oidc")
public class AuthorizationEndpoint {

    @GetMapping("/authorize")
    public ResponseEntity<String> authorize(@RequestParam String response_type,
                                            @RequestParam String client_id,
                                            @RequestParam String redirect_uri,
                                            @RequestParam String scope,
                                            @RequestParam String state) {
        // Simplified flow: directly return an auth code
        String code = "auth-code-" + System.nanoTime();
        String redirect = redirect_uri + "?code=" + code + "&state=" + state;
        return ResponseEntity.ok("Redirect to: " + redirect);
    }
}
