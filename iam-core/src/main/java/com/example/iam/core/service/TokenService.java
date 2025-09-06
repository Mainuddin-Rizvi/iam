package com.example.iam.core.service;

import com.example.iam.domain.entity.AccessToken;
import com.example.iam.domain.entity.OAuthClient;
import com.example.iam.domain.entity.RefreshToken;
import com.example.iam.domain.entity.User;
import java.util.Set;

/** Token issuance and validation */
public interface TokenService {
    AccessToken createAccessToken(User user, OAuthClient client, Set<String> scopes);
    RefreshToken createRefreshToken(User user, OAuthClient client);
    boolean validateAccessToken(String token);
}
