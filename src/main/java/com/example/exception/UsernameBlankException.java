package com.example.exception;

public class UsernameBlankException extends RuntimeException{
    public UsernameBlankException(){
        super("Invalid entry! Username should not be blank.");
    }
}