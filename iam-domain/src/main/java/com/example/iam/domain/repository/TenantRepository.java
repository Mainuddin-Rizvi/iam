package com.example.iam.domain.repository;

import com.example.iam.domain.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.Optional;

/** Repository for Tenant */
public interface TenantRepository extends JpaRepository<Tenant, UUID> {
    Optional<Tenant> findByDomain(String domain);
}
