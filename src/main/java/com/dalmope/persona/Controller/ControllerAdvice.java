package com.dalmope.persona.Controller;

import com.dalmope.persona.Configuration.ErrorCode;
import com.dalmope.persona.Model.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorModel> runtimeException(RuntimeException e) {

        if (e.getMessage().equals(ErrorCode.DUPLICATE_USER.getDescription())) {
            ErrorModel errorModel = ErrorModel.builder().statusCode(ErrorCode.DUPLICATE_USER.getCode()).message(e.getMessage()).build();
            return new ResponseEntity<>(errorModel, ErrorCode.DUPLICATE_USER.getCode());
        }

        ErrorModel errorModel = ErrorModel.builder().statusCode(HttpStatus.INTERNAL_SERVER_ERROR).message(e.getMessage()).build();
        return new ResponseEntity<>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
