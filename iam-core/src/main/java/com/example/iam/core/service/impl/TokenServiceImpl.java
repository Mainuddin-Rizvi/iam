package com.example.iam.core.service.impl;

import com.example.iam.core.service.TokenService;
import com.example.iam.domain.entity.AccessToken;
import com.example.iam.domain.entity.OAuthClient;
import com.example.iam.domain.entity.RefreshToken;
import com.example.iam.domain.entity.User;
import com.example.iam.domain.repository.AccessTokenRepository;
import com.example.iam.domain.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

/** Token service implementation */
@Service
public class TokenServiceImpl implements TokenService {

    private final AccessTokenRepository accessRepo;
    private final RefreshTokenRepository refreshRepo;

    public TokenServiceImpl(AccessTokenRepository accessRepo, RefreshTokenRepository refreshRepo) {
        this.accessRepo = accessRepo;
        this.refreshRepo = refreshRepo;
    }

    @Override
    public AccessToken createAccessToken(User user, OAuthClient client, Set<String> scopes) {
        AccessToken at = new AccessToken();
        at.setUser(user);
        at.setClient(client);
        at.setToken("atk-" + UUID.randomUUID());
        at.setExpiresAt(Instant.now().plusSeconds(3600));
        at.setRevoked(false);
        return accessRepo.save(at);
    }

    @Override
    public RefreshToken createRefreshToken(User user, OAuthClient client) {
        RefreshToken rt = new RefreshToken();
        rt.setUser(user);
        rt.setClient(client);
        rt.setToken("rtk-" + UUID.randomUUID());
        rt.setExpiresAt(Instant.now().plusSeconds(86400));
        rt.setRevoked(false);
        return refreshRepo.save(rt);
    }

    @Override
    public boolean validateAccessToken(String token) {
        return accessRepo.findByToken(token).map(t -> !t.isRevoked() && t.getExpiresAt().isAfter(Instant.now())).orElse(false);
    }
}
