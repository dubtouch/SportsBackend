package com.example.demo_extreme_sports.exception;

public class CityAlreadyPresentException extends RuntimeException{
    public CityAlreadyPresentException(String message) {
        super(message);
    }
}
