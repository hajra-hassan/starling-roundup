package com.example.starlingtest.wrapper;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionResponseWrapper implements Serializable {

    @JsonProperty("_embedded")
    private TransactionsWrapper response;

	public TransactionsWrapper getResponse() {
		return response;
	}

	public void setResponse(TransactionsWrapper response) {
		this.response = response;
	}

}