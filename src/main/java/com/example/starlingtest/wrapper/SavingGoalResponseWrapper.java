package com.example.starlingtest.wrapper;

import com.example.starlingtest.entity.Amount;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SavingGoalResponseWrapper
{

    @JsonProperty("savingsGoalUid")
    private String savingsGoalUid;
    private String name;
    @JsonProperty(value = "totalSaved")
    private Amount amount;
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
	public Amount getAmount() {
		return amount;
	}
	public void setAmount(Amount amount) {
		this.amount = amount;
	}
    
    
    
    
    
    
    

}