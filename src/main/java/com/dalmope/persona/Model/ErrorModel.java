package com.dalmope.persona.Model;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ErrorModel {
    private HttpStatus statusCode;
    private String message;
}
