package com.example.iam.domain.repository;

import com.example.iam.domain.entity.OAuthClient;
import com.example.iam.domain.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/** Repository for OAuthClient */
public interface OAuthClientRepository extends JpaRepository<OAuthClient, UUID> {
    Optional<OAuthClient> findByClientId(String clientId);
    List<OAuthClient> findByTenant(Tenant tenant);
}
