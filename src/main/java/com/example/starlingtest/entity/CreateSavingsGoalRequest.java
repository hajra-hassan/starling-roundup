package com.example.starlingtest.entity;

import java.math.BigDecimal;
import java.util.Random;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class CreateSavingsGoalRequest
{

    private String name;
    private String currency;
    private Amount target;
    private String base64EncodedPhoto;

    // All-args constructor
    public CreateSavingsGoalRequest(String name, String currency, Amount target, String base64EncodedPhoto) {
        this.name = name;
        this.currency = currency;
        this.target = target;
        this.base64EncodedPhoto = base64EncodedPhoto;
    }

    // No-args constructor
    public CreateSavingsGoalRequest() {
        // Default values or initialization logic can be placed here
    }

    // Static factory method to create instances with default values
    public static CreateSavingsGoalRequest withDefaultValues() {
        final String name = "Saving Goal " + new Random().nextInt(10000000);
        final String currency = "GBP";
        final Amount target = new Amount(currency, BigDecimal.valueOf(10000000));
        final String base64EncodedPhoto = "string";

        return new CreateSavingsGoalRequest(name, currency, target, base64EncodedPhoto);
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Amount getTarget() {
		return target;
	}

	public void setTarget(Amount target) {
		this.target = target;
	}

	public String getBase64EncodedPhoto() {
		return base64EncodedPhoto;
	}

	public void setBase64EncodedPhoto(String base64EncodedPhoto) {
		this.base64EncodedPhoto = base64EncodedPhoto;
	}

    

 


}