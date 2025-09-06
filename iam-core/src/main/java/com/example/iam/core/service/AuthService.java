package com.example.iam.core.service;

import com.example.iam.common.dto.LoginRequest;
import com.example.iam.common.dto.TokenResponse;

/** Authentication service */
public interface AuthService {
    TokenResponse authenticate(LoginRequest req);
    TokenResponse refreshToken(String refreshToken);
    void revokeToken(String token);
}
