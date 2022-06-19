package com.backend.koanba.exceptions;

public class ProductQuantityInsufficientException extends Exception {
    public ProductQuantityInsufficientException(String message) {
        super(message);
    }
}
