package com.veteriner.yonetim.sistemi.exception;

/**
 * ResourceNotFoundException - Custom exception for when a requested resource is not found
 * Used when trying to access entities that don't exist in the database
 */
public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(String message) {
        super(message);
    }
    
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s %s '%s' record not found in the system.", resourceName, fieldName, fieldValue));
    }
} 