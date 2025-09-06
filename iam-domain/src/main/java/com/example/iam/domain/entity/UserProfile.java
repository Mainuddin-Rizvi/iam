package com.example.iam.domain.entity;

import jakarta.persistence.*;
import java.util.UUID;

/** User profile entity */
@Entity
@Table(name = "user_profiles")
public class UserProfile {
    @Id
    @GeneratedValue
    private UUID id;

    private String firstName;
    private String lastName;
    private String phone;

    // Getters & Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
