package com.example.iam.core.service.impl;

import com.example.iam.common.dto.TenantDto;
import com.example.iam.common.exception.TenantNotFoundException;
import com.example.iam.core.mapper.TenantMapper;
import com.example.iam.core.service.TenantService;
import com.example.iam.domain.entity.Tenant;
import com.example.iam.domain.repository.TenantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/** Tenant service implementation */
@Service
@Transactional
public class TenantServiceImpl implements TenantService {

    private final TenantRepository tenantRepo;
    private final TenantMapper tenantMapper;

    public TenantServiceImpl(TenantRepository tenantRepo, TenantMapper tenantMapper) {
        this.tenantRepo = tenantRepo;
        this.tenantMapper = tenantMapper;
    }

    @Override
    public TenantDto createTenant(TenantDto dto) {
        Tenant tenant = tenantMapper.toEntity(dto);
        tenant.setCreatedAt(Instant.now());
        tenant.setUpdatedAt(Instant.now());
        return tenantMapper.toDto(tenantRepo.save(tenant));
    }

    @Override
    public TenantDto updateTenant(UUID id, TenantDto dto) {
        Tenant existing = tenantRepo.findById(id)
                .orElseThrow(() -> new TenantNotFoundException("Tenant not found"));
        existing.setName(dto.getName());
        existing.setDomain(dto.getDomain());
        existing.getSettings().setDefaultRole(dto.getDefaultRole());
        existing.getSettings().setOidcEnabled(dto.getOidcEnabled());
        existing.getSettings().setSamlEnabled(dto.getSamlEnabled());
        existing.setUpdatedAt(Instant.now());
        return tenantMapper.toDto(tenantRepo.save(existing));
    }

    @Override
    public void deleteTenant(UUID id) {
        tenantRepo.deleteById(id);
    }

    @Override
    public TenantDto getTenant(UUID id) {
        return tenantMapper.toDto(tenantRepo.findById(id)
                .orElseThrow(() -> new TenantNotFoundException("Tenant not found")));
    }

    @Override
    public TenantDto findByDomain(String domain) {
        return tenantMapper.toDto(tenantRepo.findByDomain(domain)
                .orElseThrow(() -> new TenantNotFoundException("Tenant domain not found")));
    }

    @Override
    public List<TenantDto> listTenants() {
        return tenantRepo.findAll().stream().map(tenantMapper::toDto).collect(Collectors.toList());
    }
}
