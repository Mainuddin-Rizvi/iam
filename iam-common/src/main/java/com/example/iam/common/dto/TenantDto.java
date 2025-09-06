package com.example.iam.common.dto;

import java.time.Instant;
import java.util.UUID;

/** DTO for transferring tenant data */
public class TenantDto {
    private UUID id;
    private String name;
    private String domain;
    private Boolean samlEnabled;
    private Boolean oidcEnabled;
    private String defaultRole;
    private Instant createdAt;
    private Instant updatedAt;

    // Getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDomain() { return domain; }
    public void setDomain(String domain) { this.domain = domain; }
    public Boolean getSamlEnabled() { return samlEnabled; }
    public void setSamlEnabled(Boolean samlEnabled) { this.samlEnabled = samlEnabled; }
    public Boolean getOidcEnabled() { return oidcEnabled; }
    public void setOidcEnabled(Boolean oidcEnabled) { this.oidcEnabled = oidcEnabled; }
    public String getDefaultRole() { return defaultRole; }
    public void setDefaultRole(String defaultRole) { this.defaultRole = defaultRole; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
