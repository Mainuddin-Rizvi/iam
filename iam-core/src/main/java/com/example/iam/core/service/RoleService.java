package com.example.iam.core.service;

import com.example.iam.common.dto.RoleDto;
import java.util.List;
import java.util.UUID;

/** Role service */
public interface RoleService {
    RoleDto createRole(UUID tenantId, RoleDto dto);
    RoleDto updateRole(UUID tenantId, UUID roleId, RoleDto dto);
    void deleteRole(UUID tenantId, UUID roleId);
    RoleDto findById(UUID tenantId, UUID roleId);
    List<RoleDto> listRoles(UUID tenantId);
}
