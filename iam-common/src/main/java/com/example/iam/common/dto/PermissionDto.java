package com.example.iam.common.dto;

import java.util.UUID;

/** DTO for permission */
public class PermissionDto {
    private UUID id;
    private String name;
    private String description;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
