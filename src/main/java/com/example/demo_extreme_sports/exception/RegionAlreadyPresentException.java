package com.example.demo_extreme_sports.exception;

public class RegionAlreadyPresentException extends RuntimeException{
    public RegionAlreadyPresentException(String message) {
        super(message);
    }
}
