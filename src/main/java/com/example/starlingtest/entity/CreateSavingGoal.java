package com.example.starlingtest.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateSavingGoal
{

    @JsonProperty("savingsGoalUid")
    private String savingsGoalUid;
    @JsonProperty("success")
    private Boolean success;
	public String getSavingsGoalUid() {
		return savingsGoalUid;
	}
	public void setSavingsGoalUid(String savingsGoalUid) {
		this.savingsGoalUid = savingsGoalUid;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
    
    
    
    

}