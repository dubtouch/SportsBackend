package com.example.demo_extreme_sports.controller;

import com.example.demo_extreme_sports.exception.*;
import com.example.demo_extreme_sports.model.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDetails> notFoundException(NotFoundException notFoundException) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage(notFoundException.getMessage() + " not found.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

    @ExceptionHandler(AlreadyPresentException.class)
    public ResponseEntity<ErrorDetails> alreadyPresentException(AlreadyPresentException alreadyPresentException) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage(alreadyPresentException.getMessage() + " is already present.");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetails);
    }
}
