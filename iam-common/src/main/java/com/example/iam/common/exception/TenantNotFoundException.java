package com.example.iam.common.exception;

/** Exception thrown when tenant not found */
public class TenantNotFoundException extends RuntimeException {
    public TenantNotFoundException(String message) { super(message); }
}
