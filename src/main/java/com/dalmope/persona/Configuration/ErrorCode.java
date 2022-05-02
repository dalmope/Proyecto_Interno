package com.dalmope.persona.Configuration;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    DUPLICATE_USER("This name or email already available in our system.", HttpStatus.BAD_REQUEST),
    INTERNAL_ERROR("Sorry, something was wrong.", HttpStatus.INTERNAL_SERVER_ERROR),
    IMAGE_NOT_FOUND("Sorry, something was wrong.", HttpStatus.BAD_REQUEST);

    private final HttpStatus code;
    private final String description;

    private ErrorCode(String description, HttpStatus code) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public HttpStatus getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }
}
