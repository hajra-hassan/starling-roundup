package com.example.starlingtest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.starlingtest.entity.SavingGoal;
import com.example.starlingtest.entity.TransactionFeed;
import com.example.starlingtest.exceptions.StarlingBankException;
import com.example.starlingtest.service.RoundUpBalanceService;
import com.example.starlingtest.service.TransactionService;


@RestController
@RequestMapping(value = "/starling-test")
public class RoundupController
{

    @Autowired private RoundUpBalanceService roundUpService;
    
    @Autowired private TransactionService transactionService;


    @GetMapping(value = "/round-up")
    public ResponseEntity roundUp()
    {
        final List<SavingGoal> response = roundUpService.roundUpTransactionsFeed();
        return ResponseEntity.ok(response);
    }
    
    




}