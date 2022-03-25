package com.example.demo_extreme_sports.exception;

public class CountryAlreadyPresentException extends RuntimeException{
    public CountryAlreadyPresentException(String message) {
        super(message);
    }
}
