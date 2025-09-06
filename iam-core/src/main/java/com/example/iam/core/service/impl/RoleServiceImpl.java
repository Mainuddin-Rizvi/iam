package com.example.iam.core.service.impl;

import com.example.iam.common.dto.RoleDto;
import com.example.iam.common.exception.TenantNotFoundException;
import com.example.iam.core.service.RoleService;
import com.example.iam.domain.entity.Role;
import com.example.iam.domain.entity.Tenant;
import com.example.iam.domain.repository.RoleRepository;
import com.example.iam.domain.repository.TenantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/** Role service implementation */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final TenantRepository tenantRepo;
    private final RoleRepository roleRepo;

    public RoleServiceImpl(TenantRepository tenantRepo, RoleRepository roleRepo) {
        this.tenantRepo = tenantRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public RoleDto createRole(UUID tenantId, RoleDto dto) {
        Tenant tenant = tenantRepo.findById(tenantId).orElseThrow(() -> new TenantNotFoundException("Tenant not found"));
        Role role = new Role();
        role.setName(dto.getName());
        role.setDescription(dto.getDescription());
        role.setTenant(tenant);
        roleRepo.save(role);
        dto.setId(role.getId());
        return dto;
    }

    @Override
    public RoleDto updateRole(UUID tenantId, UUID roleId, RoleDto dto) {
        Role role = roleRepo.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        role.setName(dto.getName());
        role.setDescription(dto.getDescription());
        return dto;
    }

    @Override
    public void deleteRole(UUID tenantId, UUID roleId) {
        roleRepo.deleteById(roleId);
    }

    @Override
    public RoleDto findById(UUID tenantId, UUID roleId) {
        Role role = roleRepo.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        RoleDto dto = new RoleDto();
        dto.setId(role.getId());
        dto.setName(role.getName());
        dto.setDescription(role.getDescription());
        return dto;
    }

    @Override
    public List<RoleDto> listRoles(UUID tenantId) {
        Tenant tenant = tenantRepo.findById(tenantId)
                .orElseThrow(() -> new TenantNotFoundException("Tenant not found"));
        return roleRepo.findByTenant(tenant).stream().map(r -> {
            RoleDto dto = new RoleDto();
            dto.setId(r.getId());
            dto.setName(r.getName());
            dto.setDescription(r.getDescription());
            return dto;
        }).collect(Collectors.toList());
    }
}
