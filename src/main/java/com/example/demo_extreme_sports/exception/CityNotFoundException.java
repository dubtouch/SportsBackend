package com.example.demo_extreme_sports.exception;

public class CityNotFoundException extends RuntimeException{
    public CityNotFoundException(String message) {
        super(message);
    }
}
