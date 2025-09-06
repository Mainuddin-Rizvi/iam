package com.example.iam.oidc.service;


import com.example.iam.common.dto.TokenResponse;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

/** Service for handling refresh tokens */
@Service
public class RefreshTokenService {

    public TokenResponse refresh(String refreshToken, String clientId) {
        TokenResponse tr = new TokenResponse();
        tr.setAccessToken("refreshed-access-" + UUID.randomUUID());
        tr.setRefreshToken(refreshToken);
        tr.setExpiresAt(Instant.now().plusSeconds(3600));
        return tr;
    }
}
