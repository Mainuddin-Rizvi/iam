package com.example.iam.core.service.impl;

import com.example.iam.core.service.AuditService;
import com.example.iam.domain.entity.AuditLog;
import com.example.iam.domain.entity.Tenant;
import com.example.iam.domain.entity.User;
import com.example.iam.domain.repository.AuditLogRepository;
import com.example.iam.domain.repository.TenantRepository;
import com.example.iam.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

/** Audit service implementation */
@Service
public class AuditServiceImpl implements AuditService {

    private final AuditLogRepository logRepo;
    private final TenantRepository tenantRepo;
    private final UserRepository userRepo;

    public AuditServiceImpl(AuditLogRepository logRepo, TenantRepository tenantRepo, UserRepository userRepo) {
        this.logRepo = logRepo;
        this.tenantRepo = tenantRepo;
        this.userRepo = userRepo;
    }

    @Override
    public void recordAction(UUID tenantId, UUID actorId, String action, String detail) {
        Tenant tenant = tenantRepo.findById(tenantId).orElseThrow();
        User actor = userRepo.findById(actorId).orElseThrow();
        AuditLog log = new AuditLog();
        log.setTenant(tenant);
        log.setActor(actor);
        log.setAction(action);
        log.setDetail(detail);
        log.setCreatedAt(Instant.now());
        logRepo.save(log);
    }

    @Override
    public List<AuditLog> getLogsForTenant(UUID tenantId) {
        Tenant tenant = tenantRepo.findById(tenantId).orElseThrow();
        return logRepo.findByTenant(tenant);
    }
}
