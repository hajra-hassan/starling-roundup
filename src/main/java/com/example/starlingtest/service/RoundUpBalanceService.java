package com.example.starlingtest.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.starlingtest.config.HeadersConfig;
import com.example.starlingtest.entity.SavingGoal;
import com.example.starlingtest.entity.TransactionFeed;
import com.example.starlingtest.exceptions.StarlingBankException;
import com.example.starlingtest.utils.APIUrls;
import com.example.starlingtest.utils.RoundupHelper;
import com.example.starlingtest.wrapper.AccountInfoResponseWrapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class RoundUpBalanceService
{
	

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private RoundupHelper roundUpHelper;
    @Autowired
    private SavingGoalService savingGoalService;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HeadersConfig headersConfiguration;


   public List<SavingGoal> roundUpTransactionsFeed()
   {
       try{
           String savingsGoalUID = "";
           final String accountId = this.getAccountUid();
           final List<TransactionFeed> transactionsList = this.transactionService.getTransactions();
           final BigDecimal roundedAmount = this.roundUpHelper.roundUpTransactionAmount(transactionsList);
           final List<SavingGoal> savingGoalsList = this.savingGoalService.getSavingGoalsList();

           if(savingGoalsList.isEmpty())
           {
               savingsGoalUID = this.savingGoalService.createNewSavingsGoal();
           }

           savingsGoalUID = savingGoalsList.get(0).getSavingsGoalUid(); // get first item in the list

           this.savingGoalService.updateSavingsGoal(savingsGoalUID, roundedAmount);

           return this.savingGoalService.getSavingGoalsList();

       }catch(StarlingBankException exception){
           throw new RuntimeException("Error with fetching data");
       }
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

        return Objects.requireNonNull(wrapper).getAccounts().get(0).getAccountId();// this way because I only have one account
    }
    /**
     * Get account id.
     * @return String with the account id
     */
    public String getDefaultCategoryUid() throws StarlingBankException {
        final HttpEntity<AccountInfoResponseWrapper> request = this.getAccountInfoCustomHeaders();
        final AccountInfoResponseWrapper wrapper = this.restTemplate
                .exchange(APIUrls.API_STARLING_BANK_ACCOUNT_INFO,
                        HttpMethod.GET,
                        request,
                        new ParameterizedTypeReference<AccountInfoResponseWrapper>() {})
                .getBody();

        return Objects.requireNonNull(wrapper).getAccounts().get(0).getDefaultCategory();// this way because I only have one account
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