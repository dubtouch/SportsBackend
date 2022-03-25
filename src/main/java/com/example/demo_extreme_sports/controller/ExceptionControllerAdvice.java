package com.example.demo_extreme_sports.controller;

import com.example.demo_extreme_sports.exception.CountryAlreadyPresentException;
import com.example.demo_extreme_sports.exception.CountryNotFoundException;
import com.example.demo_extreme_sports.exception.RegionAlreadyPresentException;
import com.example.demo_extreme_sports.exception.RegionNotFoundException;
import com.example.demo_extreme_sports.model.ErrorDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity<ErrorDetails> countryNotFoundExceptionHandler(CountryNotFoundException countryNotFoundException) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("country not found: " + countryNotFoundException.getMessage());
        return ResponseEntity.badRequest().body(errorDetails);
    }

    @ExceptionHandler(CountryAlreadyPresentException.class)
    public ResponseEntity<ErrorDetails> countryAlreadyPresentException(CountryAlreadyPresentException countryAlreadyPresent) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("country already present: " + countryAlreadyPresent.getMessage());
        return ResponseEntity.badRequest().body(errorDetails);
    }

    @ExceptionHandler(RegionNotFoundException.class)
    public ResponseEntity<ErrorDetails> regionNotFoundExceptionHandler(RegionNotFoundException regionNotFoundException) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("region not found: " + regionNotFoundException.getMessage());
        return ResponseEntity.badRequest().body(errorDetails);
    }

    @ExceptionHandler(RegionAlreadyPresentException.class)
    public ResponseEntity<ErrorDetails> regionAlreadyPresentException(RegionAlreadyPresentException regionAlreadyPresentException) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("region already present: " + regionAlreadyPresentException.getMessage());
        return ResponseEntity.badRequest().body(errorDetails);
    }
}
