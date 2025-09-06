package com.example.iam.domain.entity;

import jakarta.persistence.Embeddable;

/** Embedded tenant settings */
@Embeddable
public class TenantSettings {
    private Boolean samlEnabled;
    private Boolean oidcEnabled;
    private String defaultRole;

    public Boolean getSamlEnabled() { return samlEnabled; }
    public void setSamlEnabled(Boolean samlEnabled) { this.samlEnabled = samlEnabled; }
    public Boolean getOidcEnabled() { return oidcEnabled; }
    public void setOidcEnabled(Boolean oidcEnabled) { this.oidcEnabled = oidcEnabled; }
    public String getDefaultRole() { return defaultRole; }
    public void setDefaultRole(String defaultRole) { this.defaultRole = defaultRole; }
}
