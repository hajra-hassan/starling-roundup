package com.example.starlingtest.utils;

public class APIUrls {
	
	
	
	


	    public static final String API_STARLING_BANK_WEEKLY_TRANSACTION_URL = "https://api-sandbox.starlingbank.com/api/v2/feed/account/{accountUid}/settled-transactions-between?minTransactionTimestamp={minTransactionTimestamp}&maxTransactionTimestamp={maxTransactionTimestamp}";

	    public static final String SAVINGS_GOALS_ADD_MONEY_URL = "https://api-sandbox.starlingbank.com/api/v2/account/{accountUid}/savings-goals/{savingsGoalUid}/add-money/{transferUid}";
	    public static final String API_STARLING_BANK_ACCOUNT_INFO = "https://api-sandbox.starlingbank.com/api/v2/accounts";
	    public static final String SAVINGS_GOALS_LIST_URL = "https://api-sandbox.starlingbank.com/api/v2/account/{accountUid}/savings-goals";
	    



}
