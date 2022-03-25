package com.example.demo_extreme_sports.exception;

public class RegionNotFoundException extends RuntimeException{
    public RegionNotFoundException(String message) {
        super(message);
    }
}
