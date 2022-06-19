package com.backend.koanba.controllers.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class BaseError {
    @JsonInclude()
    private final int status;

    @JsonInclude()
    private final String message;

    private final String key;

    public BaseError(int status, String message, String key) {
        this.status = status;
        this.message = message;
        this.key = key;
    }

    public BaseError(int status, String message) {
        this.status = status;
        this.message = message;
        this.key = null;
    }
}
