package com.example.iam.core.service;

import com.example.iam.common.dto.PermissionDto;
import java.util.List;
import java.util.UUID;

/** Permission service */
public interface PermissionService {
    PermissionDto createPermission(PermissionDto dto);
    PermissionDto updatePermission(UUID id, PermissionDto dto);
    void deletePermission(UUID id);
    PermissionDto findById(UUID id);
    List<PermissionDto> listPermissions();
}
