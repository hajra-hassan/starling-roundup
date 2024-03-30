package com.example.starlingtest.wrapper;

import java.util.List;

import org.springframework.http.HttpHeaders;

import com.example.starlingtest.entity.Account;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountInfoResponseWrapper {

    @JsonProperty("accounts")
    private List<Account> accounts;

    public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
}