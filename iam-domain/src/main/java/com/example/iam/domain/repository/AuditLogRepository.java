package com.example.iam.domain.repository;

import com.example.iam.domain.entity.AuditLog;
import com.example.iam.domain.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

/** Repository for AuditLog */
public interface AuditLogRepository extends JpaRepository<AuditLog, UUID> {
    List<AuditLog> findByTenant(Tenant tenant);
}
