package com.backend.koanba.controllers.response;

import lombok.Data;

@Data
public class BaseErrorResponse {

    private final BaseError error;
    private final String message;

    public BaseErrorResponse(BaseError error) {
        this.error = error;
        this.message = null;
    }

    public BaseErrorResponse(BaseError error, String message) {
        this.error = error;
        this.message = message;
    }

    public static BaseErrorResponse genericError(int status) {
        return new BaseErrorResponse(new BaseError(status, "Couldn't process request"));
    }
}
