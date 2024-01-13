package com.example.exception;

public class DuplicateUsernameException extends RuntimeException{
    public DuplicateUsernameException(){
        super("Invalid entry! This username is already exist.");
    }
}
