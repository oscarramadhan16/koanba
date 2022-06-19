package com.backend.koanba.controllers;

import com.backend.koanba.controllers.response.BaseError;
import com.backend.koanba.controllers.response.BaseErrorResponse;
import com.backend.koanba.exceptions.CustomerNotFoundException;
import com.backend.koanba.exceptions.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {
    private static final String GENERIC_RESPONSE_ERROR = "Couldn't process request";

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<BaseErrorResponse> handleGenericError(Throwable e) {
        log.error("Something unexpected is happening", e);
        return new ResponseEntity<>(
                new BaseErrorResponse(
                        new BaseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), GENERIC_RESPONSE_ERROR)),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<BaseErrorResponse> productNotFoundException(ProductNotFoundException e) {
        log.debug(e.getMessage(), e);
        return new ResponseEntity<>(
                new BaseErrorResponse(
                        new BaseError(HttpStatus.NOT_FOUND.value(), e.getMessage())),
                        HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<BaseErrorResponse> customerNotFoundException(CustomerNotFoundException e) {
        log.debug(e.getMessage(), e);
        return new ResponseEntity<>(
                new BaseErrorResponse(
                        new BaseError(HttpStatus.NOT_FOUND.value(), e.getMessage())),
                HttpStatus.NOT_FOUND);
    }
}
