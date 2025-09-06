package com.example.iam.common.dto;

/** DTO representing a processed SAML response */
public class SamlResponseDto {
    private String nameId;
    private String sessionIndex;

    public String getNameId() { return nameId; }
    public void setNameId(String nameId) { this.nameId = nameId; }
    public String getSessionIndex() { return sessionIndex; }
    public void setSessionIndex(String sessionIndex) { this.sessionIndex = sessionIndex; }
}
