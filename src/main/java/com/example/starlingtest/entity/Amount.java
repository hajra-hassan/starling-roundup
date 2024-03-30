package com.example.starlingtest.entity;

import java.math.BigDecimal;
import java.util.Currency;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Amount {
	
	public Amount() {
		
	}
	
	public Amount(String currency, BigDecimal minorUnits) {
		super();
		this.currency = currency;
		this.minorUnits = minorUnits;
	}
    @JsonProperty("currency")
	String currency;
    @JsonProperty("minorUnits")
	BigDecimal minorUnits;
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public BigDecimal getMinorUnits() {
		return minorUnits;
	}
	public void setMinorUnits(BigDecimal bigDecimal) {
		this.minorUnits = bigDecimal;
	}



}
