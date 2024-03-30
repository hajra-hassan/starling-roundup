package com.example.starlingtest.wrapper;

import java.util.List;

import com.example.starlingtest.entity.SavingGoal;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SavingGoalWrapper
{

    @JsonProperty("savingsGoalList")
    private List<SavingGoal> savingsGoalList;

	public List<SavingGoal> getSavingsGoalList() {
		return savingsGoalList;
	}

	public void setSavingsGoalList(List<SavingGoal> savingsGoalList) {
		this.savingsGoalList = savingsGoalList;
	}
    
    
    

}