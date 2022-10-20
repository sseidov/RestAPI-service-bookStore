package com.example.RestAPIservicebookStore.exception;

public class CustomerDoesntExistException extends Exception{
    public CustomerDoesntExistException(String message) {
        super(message);
    }
}
