package com.example.iam.domain.repository;

import com.example.iam.domain.entity.RefreshToken;
import com.example.iam.domain.entity.OAuthClient;
import com.example.iam.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

/** Repository for RefreshToken */
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByUserAndClient(User user, OAuthClient client);
}
