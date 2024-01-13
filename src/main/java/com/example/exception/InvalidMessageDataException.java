package com.example.exception;

public class InvalidMessageDataException extends RuntimeException{

    public InvalidMessageDataException(){
        super("The text message should not be blank, and should be under 255 characters.");
    }
    
}
