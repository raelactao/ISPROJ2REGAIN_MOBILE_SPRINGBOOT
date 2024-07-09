package com.isproj2.regainmobile.model;

import org.springframework.http.HttpStatus;

public class RegainmobileApiException extends RuntimeException {
    private final HttpStatus status;
    private final String message;

    public RegainmobileApiException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
