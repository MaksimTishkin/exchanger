package com.alfa.tishkin.advice;

import com.alfa.tishkin.exception.IncorrectCurrencyCodeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(IncorrectCurrencyCodeException.class)
    public ResponseEntity<String> handleIncorrectCurrencyCodeException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
