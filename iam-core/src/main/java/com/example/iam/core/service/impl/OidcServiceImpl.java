package com.example.iam.core.service.impl;

import com.example.iam.common.dto.TokenResponse;
import com.example.iam.core.service.OidcService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

/** OIDC service implementation */
@Service
public class OidcServiceImpl implements OidcService {
    @Override
    public String discovery(UUID tenantId) {
        return "{ \"issuer\": \"http://localhost:8080/realms/" + tenantId + "\" }";
    }

    @Override
    public TokenResponse issueOidcTokens(String authorizationCode, String clientId) {
        TokenResponse tr = new TokenResponse();
        tr.setAccessToken("oidc-access-" + authorizationCode);
        tr.setRefreshToken("oidc-refresh-" + authorizationCode);
        tr.setExpiresAt(Instant.now().plusSeconds(3600));
        return tr;
    }
}
