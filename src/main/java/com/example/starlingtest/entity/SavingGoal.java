package com.example.starlingtest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class SavingGoal {

    private String savingsGoalUid;
    private String name;
    private Amount totalSaved;
    private String savedPercentage;

    // No-args constructor
    public SavingGoal() {
        // Initialization logic can be placed here if needed
    }

    // All-args constructor
    public SavingGoal(String savingsGoalUid, String name, Amount totalSaved, String savedPercentage) {
        this.savingsGoalUid = savingsGoalUid;
        this.name = name;
        this.totalSaved = totalSaved;
        this.savedPercentage = savedPercentage;
    }

    // Getters and setters
    public String getSavingsGoalUid() {
        return savingsGoalUid;
    }

    public void setSavingsGoalUid(String savingsGoalUid) {
        this.savingsGoalUid = savingsGoalUid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Amount getTotalSaved() {
        return totalSaved;
    }

    public void setTotalSaved(Amount totalSaved) {
        this.totalSaved = totalSaved;
    }

    public String getSavedPercentage() {
        return savedPercentage;
    }

    public void setSavedPercentage(String savedPercentage) {
        this.savedPercentage = savedPercentage;
    }
}