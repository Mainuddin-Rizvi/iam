package com.example.iam.saml.controller;

import com.example.iam.common.dto.SamlResponseDto;
import com.example.iam.core.service.SamlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/** REST endpoints for SAML login flow */
@RestController
@RequestMapping("/saml")
public class SamlController {

    private final SamlService samlService;

    public SamlController(SamlService samlService) {
        this.samlService = samlService;
    }

    @GetMapping("/auth/{tenantId}")
    public ResponseEntity<String> startSamlAuth(@PathVariable UUID tenantId,
                                                @RequestParam(defaultValue = "defaultRelay") String relayState) {
        return ResponseEntity.ok(samlService.generateAuthnRequest(tenantId, relayState));
    }

    @PostMapping("/acs")
    public ResponseEntity<SamlResponseDto> consumeResponse(@RequestParam String samlResponse) {
        return ResponseEntity.ok(samlService.processSamlResponse(samlResponse));
    }

    @GetMapping("/metadata/{tenantId}")
    public ResponseEntity<String> metadata(@PathVariable UUID tenantId) {
        return ResponseEntity.ok(samlService.getMetadata(tenantId));
    }
}
