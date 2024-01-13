package com.example.exception;

public class UnauthorizedLoginException extends RuntimeException{

    public UnauthorizedLoginException(){
        super("Unauthorized! Login ceredentials provided do not exist.");
    }
    
}
