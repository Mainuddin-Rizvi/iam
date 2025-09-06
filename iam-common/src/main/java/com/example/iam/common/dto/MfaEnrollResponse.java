package com.example.iam.common.dto;

import java.util.UUID;

/** DTO returned when enrolling a user into MFA */
public class MfaEnrollResponse {
    private UUID userId;
    private String type;
    private String secret;

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getSecret() { return secret; }
    public void setSecret(String secret) { this.secret = secret; }
}
