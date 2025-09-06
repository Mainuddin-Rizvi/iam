package com.example.iam.core.service.impl;

import com.example.iam.common.dto.LoginRequest;
import com.example.iam.common.dto.TokenResponse;
import com.example.iam.common.exception.InvalidTokenException;
import com.example.iam.core.service.AuthService;
import com.example.iam.core.service.TokenService;
import com.example.iam.domain.entity.User;
import com.example.iam.domain.entity.OAuthClient;
import com.example.iam.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Collections;

/** Auth service implementation */
@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final TokenService tokenService;

    public AuthServiceImpl(UserRepository userRepo, PasswordEncoder encoder,
                           TokenService tokenService) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.tokenService = tokenService;
    }

    @Override
    public TokenResponse authenticate(LoginRequest req) {
        User user = userRepo.findAll().stream()
                .filter(u -> u.getUsername().equals(req.getUsername()))
                .findFirst().orElseThrow(() -> new RuntimeException("Invalid login"));
        if (!encoder.matches(req.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid password");
        }
        OAuthClient fakeClient = new OAuthClient(); // simplified for demo
        var access = tokenService.createAccessToken(user, fakeClient, Collections.singleton("read"));
        var refresh = tokenService.createRefreshToken(user, fakeClient);
        TokenResponse tr = new TokenResponse();
        tr.setAccessToken(access.getToken());
        tr.setRefreshToken(refresh.getToken());
        tr.setExpiresAt(Instant.now().plusSeconds(3600));
        return tr;
    }

    @Override
    public TokenResponse refreshToken(String refreshToken) {
        // naive refresh flow
        TokenResponse tr = new TokenResponse();
        tr.setAccessToken("new-access-" + refreshToken);
        tr.setRefreshToken(refreshToken);
        tr.setExpiresAt(Instant.now().plusSeconds(3600));
        return tr;
    }

    @Override
    public void revokeToken(String token) {
        throw new InvalidTokenException("Revocation not yet implemented");
    }
}
