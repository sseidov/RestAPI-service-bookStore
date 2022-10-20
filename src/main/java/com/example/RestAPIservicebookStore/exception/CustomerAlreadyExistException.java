package com.example.RestAPIservicebookStore.exception;

public class CustomerAlreadyExistException extends Exception{
    public CustomerAlreadyExistException(String message) {
        super(message);
    }
}
