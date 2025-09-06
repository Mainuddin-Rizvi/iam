package com.example.iam.core.service;

import com.example.iam.common.dto.TenantDto;
import java.util.List;
import java.util.UUID;

/** Tenant service */
public interface TenantService {
    TenantDto createTenant(TenantDto dto);
    TenantDto updateTenant(UUID id, TenantDto dto);
    void deleteTenant(UUID id);
    TenantDto getTenant(UUID id);
    TenantDto findByDomain(String domain);
    List<TenantDto> listTenants();
}
