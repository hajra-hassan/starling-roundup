package com.example.starlingtest.wrapper;

import java.io.Serializable;

import com.example.starlingtest.entity.Amount;
import lombok.Data;

@Data
public class AmountResponseWrapper implements Serializable {

    private Amount amount;

	public Amount getAmount() {
		return amount;
	}

	public void setAmount(Amount amount) {
		this.amount = amount;
	}
}
