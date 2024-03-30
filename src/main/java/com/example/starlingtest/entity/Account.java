package com.example.starlingtest.entity;

import java.time.OffsetDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


	@Data
	public class Account {


	    @JsonProperty("accountUid")
	    private String accountId;
	    @JsonProperty("defaultCategory")
	    private String defaultCategory;
	    @JsonProperty("currency")
	    private String currency;
	    @JsonProperty("createdAt")
	    private OffsetDateTime createdAt;
		public String getAccountId() {
			return accountId;
		}
		public void setAccountId(String accountId) {
			this.accountId = accountId;
		}
		public String getDefaultCategory() {
			return defaultCategory;
		}
		public void setDefaultCategory(String defaultCategory) {
			this.defaultCategory = defaultCategory;
		}
		public String getCurrency() {
			return currency;
		}
		public void setCurrency(String currency) {
			this.currency = currency;
		}
		public OffsetDateTime getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(OffsetDateTime createdAt) {
			this.createdAt = createdAt;
		}
	    
	    
	    
	    
	
}
