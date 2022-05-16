package com.dalmope.persona.controller;

import com.dalmope.persona.configuration.Messagges;
import com.dalmope.persona.exceptions.ObjectNotFoundException;
import com.dalmope.persona.model.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * This class is a global exception handler for all the exceptions thrown by the application
 */
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErrorModel> handlePersonNotFoundException(ObjectNotFoundException ex) {
        ErrorModel errorModel = ErrorModel.builder().status(HttpStatus.NOT_FOUND).message(ex.getMessage()).build();
        return new ResponseEntity<>(errorModel, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorModel> runtimeException(RuntimeException e) {

        Messagges messagges = Messagges.getErrorCode(e.getMessage());

        if (messagges != null) {
            ErrorModel errorModel = ErrorModel.builder().status(messagges.getCode()).message(e.getMessage()).build();
            return new ResponseEntity<>(errorModel, messagges.getCode());
        }

        ErrorModel errorModel = ErrorModel.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(e.getMessage()).build();
        return new ResponseEntity<>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
