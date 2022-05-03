package com.dalmope.persona.Controller;

import com.dalmope.persona.Configuration.ErrorCode;
import com.dalmope.persona.Model.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * This class is a global exception handler for all the exceptions thrown by the application
 */
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorModel> runtimeException(RuntimeException e) {

        ErrorCode errorCode = ErrorCode.getErrorCode(e.getMessage());

        if (errorCode != null) {
            ErrorModel errorModel = ErrorModel.builder().status(errorCode.getCode()).message(e.getMessage()).build();
            return new ResponseEntity<>(errorModel, errorCode.getCode());
        }

        ErrorModel errorModel = ErrorModel.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(e.getMessage()).build();
        return new ResponseEntity<>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
