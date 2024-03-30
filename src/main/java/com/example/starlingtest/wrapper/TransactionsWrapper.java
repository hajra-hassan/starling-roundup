package com.example.starlingtest.wrapper;

import java.io.Serializable;
import java.util.List;

import com.example.starlingtest.entity.TransactionFeed;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionsWrapper implements Serializable
{

    @JsonProperty("feedItems")
    private List<TransactionFeed> transactions;

	public List<TransactionFeed> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionFeed> transactions) {
		this.transactions = transactions;
	}

}

	

