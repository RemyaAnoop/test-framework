package com.project.automation.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.automation.utils.ApiUtils;

/**
 * This class represents a User model used in API requests and responses.
 * It includes fields for user details such as id, name, email, accountType, status, and createdAt.
 * The class provides constructors, getters, and setters for these fields.
 * It is to facilitate serialization and deserialization of user data in JSON format.
 */
public class User {
    private String id;
    private String name;
    private String email;
    private String createdAt;
    private String status;

    @JsonProperty("accountType")
    private String accountType;

    public User() {}

    public User(String name, String email, String accountType) {
        this.name = name;
        this.email = email;
        this.accountType = accountType;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        if (!ApiUtils.isEmail(email)) {
            throw new IllegalArgumentException("Invalid email format: " + email);
        }
        this.email = email;
    }

    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return String.format("User{id='%s', name='%s', email='%s', accountType='%s'}",
                id, name, email, accountType);
    }
}
