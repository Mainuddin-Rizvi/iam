package com.example.iam.core.service;

import com.example.iam.common.dto.SamlResponseDto;
import java.util.UUID;

/** SAML Service */
public interface SamlService {
    String generateAuthnRequest(UUID tenantId, String relayState);
    SamlResponseDto processSamlResponse(String samlResponse);
    String getMetadata(UUID tenantId);
}
