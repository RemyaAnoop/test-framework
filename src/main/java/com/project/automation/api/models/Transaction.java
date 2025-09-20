package com.project.automation.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/**
 * This class represents a Transaction model used in API requests and responses.
 * It includes fields for transaction details such as userId, amount, type, recipientId, status, and createdAt.
 * The class provides constructors, getters, and setters for these fields.
 * It is designed to facilitate serialization and deserialization of transaction data in JSON format.
 */
public class Transaction {

    private String id;

    @JsonProperty("userId")
    private String userId;
    @JsonProperty("recipientId")
    private String recipientId;

    private BigDecimal amount;
    private String type;
    private String status;
    private String createdAt;

    public Transaction() {}

    public Transaction(String userId, BigDecimal amount, String type, String recipientId) {
        this.userId = userId;
        this.amount = amount;
        this.type = type;
        this.recipientId = recipientId;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getRecipientId() { return recipientId; }
    public void setRecipientId(String recipientId) { this.recipientId = recipientId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
