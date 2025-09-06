package com.example.iam.domain.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

/** Refresh token entity */
@Entity
@Table(name = "refresh_tokens")
public class RefreshToken {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private User user;

    @ManyToOne
    private OAuthClient client;

    @Column(nullable = false, unique = true)
    private String token;

    private Instant expiresAt;
    private boolean revoked;

    // Getters & Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public OAuthClient getClient() { return client; }
    public void setClient(OAuthClient client) { this.client = client; }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public Instant getExpiresAt() { return expiresAt; }
    public void setExpiresAt(Instant expiresAt) { this.expiresAt = expiresAt; }
    public boolean isRevoked() { return revoked; }
    public void setRevoked(boolean revoked) { this.revoked = revoked; }
}
