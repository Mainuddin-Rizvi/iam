package com.example.iam.oidc.model;


import com.example.iam.common.dto.TokenResponse;

/** Generic token store abstraction */
public interface TokenStore {
    void storeToken(String clientId, TokenResponse token);
    TokenResponse getToken(String clientId);
}
