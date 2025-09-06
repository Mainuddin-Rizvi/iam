package com.example.iam.core.service;

import com.example.iam.common.dto.TokenResponse;
import java.util.UUID;

/** OIDC Service */
public interface OidcService {
    String discovery(UUID tenantId);
    TokenResponse issueOidcTokens(String authorizationCode, String clientId);
}
