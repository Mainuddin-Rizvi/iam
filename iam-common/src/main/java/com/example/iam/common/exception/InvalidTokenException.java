package com.example.iam.common.exception;

/** Exception for invalid tokens */
public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String message) { super(message); }
}
