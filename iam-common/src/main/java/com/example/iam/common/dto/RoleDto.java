package com.example.iam.common.dto;

import java.util.Set;
import java.util.UUID;

/** DTO for role */
public class RoleDto {
    private UUID id;
    private UUID tenantId;
    private String name;
    private String description;
    private Set<PermissionDto> permissions;

    // Getters & Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getTenantId() { return tenantId; }
    public void setTenantId(UUID tenantId) { this.tenantId = tenantId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Set<PermissionDto> getPermissions() { return permissions; }
    public void setPermissions(Set<PermissionDto> permissions) { this.permissions = permissions; }
}
