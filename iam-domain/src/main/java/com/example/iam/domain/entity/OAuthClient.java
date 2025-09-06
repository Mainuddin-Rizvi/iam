package com.example.iam.domain.entity;

import jakarta.persistence.*;
import java.util.Set;
import java.util.UUID;

/** OAuth client entity */
@Entity
@Table(name = "oauth_clients")
public class OAuthClient {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    private Tenant tenant;

    @Column(nullable = false, unique = true)
    private String clientId;

    private String clientSecret;

    @ElementCollection
    private Set<String> redirectUris;

    @ElementCollection
    private Set<String> scopes;

    private boolean confidential;

    // Getters & Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public Tenant getTenant() { return tenant; }
    public void setTenant(Tenant tenant) { this.tenant = tenant; }
    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }
    public String getClientSecret() { return clientSecret; }
    public void setClientSecret(String clientSecret) { this.clientSecret = clientSecret; }
    public Set<String> getRedirectUris() { return redirectUris; }
    public void setRedirectUris(Set<String> redirectUris) { this.redirectUris = redirectUris; }
    public Set<String> getScopes() { return scopes; }
    public void setScopes(Set<String> scopes) { this.scopes = scopes; }
    public boolean isConfidential() { return confidential; }
    public void setConfidential(boolean confidential) { this.confidential = confidential; }
}
