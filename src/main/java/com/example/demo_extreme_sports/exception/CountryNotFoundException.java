package com.example.demo_extreme_sports.exception;

public class CountryNotFoundException extends RuntimeException{
    public CountryNotFoundException(String message) {
        super(message);
    }
}
