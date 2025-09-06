package com.example.iam.oidc.service;


import com.example.iam.common.dto.TokenResponse;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

/** Issues JWT tokens (simplified) */
@Service
public class JwtTokenService {

    public TokenResponse issue(String clientId) {
        TokenResponse tr = new TokenResponse();
        tr.setAccessToken("jwt-" + UUID.randomUUID());
        tr.setRefreshToken("rjwt-" + UUID.randomUUID());
        tr.setExpiresAt(Instant.now().plusSeconds(3600));
        return tr;
    }
}
