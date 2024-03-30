package com.example.starlingtest.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.starlingtest.config.HeadersConfig;
import com.example.starlingtest.entity.Amount;
import com.example.starlingtest.entity.CreateSavingGoal;
import com.example.starlingtest.entity.CreateSavingsGoalRequest;
import com.example.starlingtest.entity.SavingGoal;
import com.example.starlingtest.exceptions.StarlingBankException;
import com.example.starlingtest.utils.APIUrls;
import com.example.starlingtest.wrapper.SavingGoalWrapper;
import com.example.starlingtest.wrapper.AccountInfoResponseWrapper;
import com.example.starlingtest.wrapper.AmountResponseWrapper;


import java.math.BigDecimal;
import java.util.*;
@Service
public class SavingGoalService
{

    @Autowired private RestTemplate restTemplate;
    @Autowired private HeadersConfig headersConfiguration;

    public List<SavingGoal> getSavingGoalsList() throws StarlingBankException
    {
        final HttpEntity<SavingGoalWrapper> request = this.getHeaders();
        final Map<String, String> params = new HashMap<>();
        final String accountId = this.getAccountUid();

        params.put("accountUid", accountId);

        final ResponseEntity<SavingGoalWrapper> response = this.restTemplate.exchange(APIUrls.SAVINGS_GOALS_LIST_URL,
                HttpMethod.GET,
                request,
                SavingGoalWrapper.class, params);

        if(response.getBody() == null)
        {
            return Collections.emptyList();
        }

        return response.getBody().getSavingsGoalList();
    }

    public String createNewSavingsGoal() throws StarlingBankException
    {
        final HttpEntity<CreateSavingsGoalRequest> request = this.putHeaders();

        final Map<String, String> params = new HashMap<>();
        final String accountId = this.getAccountUid();

        params.put("accountUid", accountId);

        final ResponseEntity<CreateSavingGoal> response = this.restTemplate.exchange(APIUrls.SAVINGS_GOALS_LIST_URL,
                HttpMethod.PUT,
                request,
                CreateSavingGoal.class, params);

        return response.getBody().getSavingsGoalUid();
    }

    public void updateSavingsGoal( final String savingsGoalUID, final BigDecimal roundedAmount) throws StarlingBankException
    {
        final Map<String, String> params = new HashMap<>();
        final String transferUID = UUID.randomUUID().toString();
        final String accountId = this.getAccountUid();


        params.put("savingsGoalUid", savingsGoalUID);
        params.put("transferUid", transferUID);
        params.put("accountUid", accountId);

        final HttpEntity<AmountResponseWrapper> request = this.putAmountCustomHeaders(roundedAmount);
        this.restTemplate.exchange(APIUrls.SAVINGS_GOALS_ADD_MONEY_URL, HttpMethod.PUT, request, String.class, params);
    }

    private HttpEntity<SavingGoalWrapper> getHeaders()
    {
        final HttpHeaders httpHeaders = this.headersConfiguration.getHeaders();
        return new HttpEntity<>(httpHeaders);
    }

    private HttpEntity<CreateSavingsGoalRequest> putHeaders()
    {
        final HttpHeaders httpHeaders = this.headersConfiguration.getHeaders();
        return new HttpEntity<>(CreateSavingsGoalRequest.withDefaultValues(), httpHeaders);
    }

    private HttpEntity<AmountResponseWrapper> putAmountCustomHeaders(final BigDecimal roundedAmount)
    {
        final HttpHeaders httpHeaders = this.headersConfiguration.getHeaders();

        final AmountResponseWrapper amount = new AmountResponseWrapper();
        final Amount newAmount = new Amount();

        newAmount.setMinorUnits(roundedAmount.movePointRight(2));
        newAmount.setCurrency("GBP"); 
        amount.setAmount(newAmount);

        return new HttpEntity<>(amount, httpHeaders);
    }
    
    
    /**
     * Get account id.
     * @return String with the account id
     */
    public String getAccountUid() throws StarlingBankException {
        final HttpEntity<AccountInfoResponseWrapper> request = this.getAccountInfoCustomHeaders();
        final AccountInfoResponseWrapper wrapper = this.restTemplate
                .exchange(APIUrls.API_STARLING_BANK_ACCOUNT_INFO,
                        HttpMethod.GET,
                        request,
                        new ParameterizedTypeReference<AccountInfoResponseWrapper>() {})
                .getBody();

        return Objects.requireNonNull(wrapper).getAccounts().get(0).getAccountId(); // this way because I only have one account
    }
    
    /**
     * Get headers.
     * @return HttpEntity
     */
    private HttpEntity<AccountInfoResponseWrapper> getAccountInfoCustomHeaders() {
        final HttpHeaders httpHeaders = this.headersConfiguration.getHeaders();
        return new HttpEntity<>(httpHeaders);
    }

}