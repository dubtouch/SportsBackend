package com.example.demo_extreme_sports.controller;

import com.example.demo_extreme_sports.exception.*;
import com.example.demo_extreme_sports.model.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity<ErrorDetails> countryNotFoundExceptionHandler(CountryNotFoundException countryNotFoundException) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("country not found: " + countryNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

    @ExceptionHandler(CountryAlreadyPresentException.class)
    public ResponseEntity<ErrorDetails> countryAlreadyPresentException(CountryAlreadyPresentException countryAlreadyPresent) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("country already present: " + countryAlreadyPresent.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetails);
    }

    @ExceptionHandler(RegionNotFoundException.class)
    public ResponseEntity<ErrorDetails> regionNotFoundExceptionHandler(RegionNotFoundException regionNotFoundException) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("region not found: " + regionNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

    @ExceptionHandler(RegionAlreadyPresentException.class)
    public ResponseEntity<ErrorDetails> regionAlreadyPresentException(RegionAlreadyPresentException regionAlreadyPresentException) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("region already present: " + regionAlreadyPresentException.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetails);
    }

    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<ErrorDetails> cityNotFoundExceptionHandler(CityNotFoundException cityNotFoundException) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("city not found: " + cityNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

    @ExceptionHandler(CityAlreadyPresentException.class)
    public ResponseEntity<ErrorDetails> cityAlreadyPresentException(CityAlreadyPresentException cityAlreadyPresentException) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("city already present: " + cityAlreadyPresentException.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetails);
    }

    @ExceptionHandler(ExtremeSportNotFoundException.class)
    public ResponseEntity<ErrorDetails> extremeSportNotFoundExceptionHandler(ExtremeSportNotFoundException extremeSportNotFoundException) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("extreme sport not found: " + extremeSportNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

    @ExceptionHandler(ExtremeSportAlreadyPresent.class)
    public ResponseEntity<ErrorDetails> extremeSportAlreadyPresentException(ExtremeSportAlreadyPresent extremeSportAlreadyPresent) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("extreme sport already present: " + extremeSportAlreadyPresent.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetails);
    }
}
