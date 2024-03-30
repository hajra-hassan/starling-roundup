package com.example.starlingtest.entity;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionFeed implements Serializable {

    @JsonProperty("categoryUid")
    private String categoryUid;
    
    @JsonProperty("sourceAmount")
    private Amount balance;
    
    @JsonProperty("direction")
    private String direction;
    
    @JsonProperty("source")
    private String source;

    // All-args constructor
    public TransactionFeed(String categoryUid, Amount balance, String direction, String source) {
        this.categoryUid = categoryUid;
        this.balance = balance;
        this.direction = direction;
        this.source = source;
    }

    // No-args constructor
    public TransactionFeed() {
    }

    // Getters and setters
    public String getCategoryUid() {
        return categoryUid;
    }

    public void setCategoryUid(String categoryUid) {
        this.categoryUid = categoryUid;
    }

    public Amount getBalance() {
        return balance;
    }

    public void setBalance(Amount balance) {
        this.balance = balance;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
