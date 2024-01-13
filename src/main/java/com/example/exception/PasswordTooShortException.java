package com.example.exception;

public class PasswordTooShortException extends RuntimeException{
    public PasswordTooShortException(){
        super("Invalid entry! Password should be at least 4 characters long.");
    }
}
