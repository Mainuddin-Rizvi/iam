package com.example.iam.domain.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

/** Tenant entity */
@Entity
@Table(name = "tenants")
public class Tenant {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String domain;

    @Embedded
    private TenantSettings settings;

    private Instant createdAt;
    private Instant updatedAt;

    // Getters & Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDomain() { return domain; }
    public void setDomain(String domain) { this.domain = domain; }
    public TenantSettings getSettings() { return settings; }
    public void setSettings(TenantSettings settings) { this.settings = settings; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
