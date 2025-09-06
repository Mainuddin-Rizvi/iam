package com.example.iam.common.exception;

/** Exception for access denied */
public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(String message) { super(message); }
}
