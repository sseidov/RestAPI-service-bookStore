package com.example.RestAPIservicebookStore.exception;

public class AuthorAlreadyExistException extends Exception{
    public AuthorAlreadyExistException(String message) {
        super(message);
    }
}
