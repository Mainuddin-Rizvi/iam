package com.example.iam.common.dto;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

/** DTO for transferring user data */
public class UserDto {
    private UUID id;
    private UUID tenantId;
    private String username;
    private String email;
    private Boolean enabled;
    private Boolean locked;
    private Set<RoleDto> roles;
    private String firstName;
    private String lastName;
    private String phone;
    private Instant createdAt;
    private Instant updatedAt;

    // Getters & Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getTenantId() { return tenantId; }
    public void setTenantId(UUID tenantId) { this.tenantId = tenantId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Boolean getEnabled() { return enabled; }
    public void setEnabled(Boolean enabled) { this.enabled = enabled; }
    public Boolean getLocked() { return locked; }
    public void setLocked(Boolean locked) { this.locked = locked; }
    public Set<RoleDto> getRoles() { return roles; }
    public void setRoles(Set<RoleDto> roles) { this.roles = roles; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
