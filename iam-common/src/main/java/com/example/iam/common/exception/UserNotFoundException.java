package com.example.iam.common.exception;

/** Exception thrown when user not found */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) { super(message); }
}
