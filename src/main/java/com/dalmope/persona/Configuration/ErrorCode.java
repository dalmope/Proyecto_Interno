package com.dalmope.persona.Configuration;

import org.springframework.http.HttpStatus;

import java.util.Objects;

public enum ErrorCode {
    DUPLICATE_USER("This name or email already available in our system.", HttpStatus.BAD_REQUEST),
    INTERNAL_ERROR("Sorry, something was wrong.", HttpStatus.INTERNAL_SERVER_ERROR),
    IMAGE_NOT_FOUND("Sorry, This image is not available.", HttpStatus.BAD_REQUEST),
    PERSON_NOT_FOUND("Sorry, This user is not available.", HttpStatus.BAD_REQUEST);

    private final HttpStatus code;
    private final String description;

    ErrorCode(String description, HttpStatus code) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public HttpStatus getCode() {
        return code;
    }

    public static ErrorCode getErrorCode(String Description) {
        for (ErrorCode errorCode : ErrorCode.values()) {
            if (Objects.equals(errorCode.description, Description)) {
                return errorCode;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }

}
