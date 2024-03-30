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
import com.example.starlingtest.entity.TransactionFeed;
import com.example.starlingtest.exceptions.StarlingBankException;
import com.example.starlingtest.utils.APIUrls;
import com.example.starlingtest.wrapper.AccountInfoResponseWrapper;
import com.example.starlingtest.wrapper.TransactionsWrapper;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class TransactionService
{

    @Autowired private RestTemplate restTemplate;
    @Autowired private HeadersConfig headersConfiguration;

    public List<TransactionFeed> getTransactions() throws StarlingBankException
    {
        final Map<String, String> params = new HashMap<>();
        final String accountId = this.getAccountUid();

        params.put("accountUid", accountId);
        params.put("minTransactionTimestamp", getDate(true));
        params.put("maxTransactionTimestamp", getDate(false));

        final HttpEntity<TransactionsWrapper> request = this.getHeaders();
        
        final ResponseEntity<TransactionsWrapper> response = this.restTemplate.exchange(APIUrls.API_STARLING_BANK_WEEKLY_TRANSACTION_URL,
                HttpMethod.GET,
                request,
                TransactionsWrapper.class, params);
               

        if(response.getBody() == null)
        {
            return Collections.emptyList();
        }

        return response.getBody().getTransactions();

    }

    public String getDate(final boolean isWeekAgoDate){
        final SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        final Date currentDate = new Date();
        Timestamp timestamp;

        if(!isWeekAgoDate){
            timestamp =new Timestamp(currentDate.getTime());
        }else{
            final LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().minusDays(7);
            final Date currentMinusSevenDays = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
            timestamp =new Timestamp(currentMinusSevenDays.getTime());
        }


        return today.format(timestamp);
    }

    private HttpEntity<TransactionsWrapper> getHeaders()
    {
        final HttpHeaders httpHeaders = this.headersConfiguration.getHeaders();
        return new HttpEntity<>(httpHeaders);
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