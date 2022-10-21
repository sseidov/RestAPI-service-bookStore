package com.example.RestAPIservicebookStore.exception;

public class BookDoesntExistException extends Exception{
    public BookDoesntExistException(String message) {
        super(message);
    }
}
