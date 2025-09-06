package com.example.iam.domain.repository;

import com.example.iam.domain.entity.AccessToken;
import com.example.iam.domain.entity.OAuthClient;
import com.example.iam.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

/** Repository for AccessToken */
public interface AccessTokenRepository extends JpaRepository<AccessToken, UUID> {
    Optional<AccessToken> findByToken(String token);
    void deleteByUserAndClient(User user, OAuthClient client);
}
