package com.example.demo_extreme_sports.exception;

public class AlreadyPresentException extends RuntimeException{
    public AlreadyPresentException(String message) {
        super(message);
    }
}
