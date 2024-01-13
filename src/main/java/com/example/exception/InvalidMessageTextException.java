package com.example.exception;

public class InvalidMessageTextException extends RuntimeException{

    public InvalidMessageTextException(){
        super("The text message should not be blank, and should be under 255 characters.");
    }
    
}
