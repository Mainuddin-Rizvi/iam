package com.example.iam.core.service;

import com.example.iam.domain.entity.AuditLog;
import java.util.List;
import java.util.UUID;

/** Auditing service */
public interface AuditService {
    void recordAction(UUID tenantId, UUID actorId, String action, String detail);
    List<AuditLog> getLogsForTenant(UUID tenantId);
}
