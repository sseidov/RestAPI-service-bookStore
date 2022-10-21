package com.example.RestAPIservicebookStore.exception;

public class AuthorDoesntExistException extends Exception{
    public AuthorDoesntExistException(String message) {
        super(message);
    }
}
