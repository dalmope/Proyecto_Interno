package com.dalmope.persona.configuration;

import org.springframework.http.HttpStatus;

import java.util.Objects;

public enum Messagges {
    DUPLICATE_USER("This name or email already available in our system.", HttpStatus.BAD_REQUEST),
    INTERNAL_ERROR("Sorry, something was wrong.", HttpStatus.INTERNAL_SERVER_ERROR),
    IMAGE_NOT_FOUND("Sorry, This image is not available.", HttpStatus.NOT_FOUND),
    IMAGE_DELETED("Image deleted", HttpStatus.OK),
    PERSON_NOT_FOUND("Sorry, This user is not available.", HttpStatus.NOT_FOUND),
    PERSON_DELETED("Person deleted", HttpStatus.OK);

    private final HttpStatus code;
    private final String description;

    Messagges(String description, HttpStatus code) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public HttpStatus getCode() {
        return code;
    }

    public static Messagges getErrorCode(String description) {
        for (Messagges messagges : Messagges.values()) {
            if (Objects.equals(messagges.description, description)) {
                return messagges;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }

}
