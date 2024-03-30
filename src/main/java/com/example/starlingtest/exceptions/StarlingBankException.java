package com.example.starlingtest.exceptions;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public class StarlingBankException extends Exception {

    public StarlingBankException(String message) {
        super(message);
    }

    public StarlingBankException(String message, Throwable cause) {
        super(message, cause);
    }

    public StarlingBankException(Throwable cause) {
        super(cause);
    }
}