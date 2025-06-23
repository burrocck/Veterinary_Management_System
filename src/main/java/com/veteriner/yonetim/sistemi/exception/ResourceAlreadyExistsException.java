package com.veteriner.yonetim.sistemi.exception;

/**
 * ResourceAlreadyExistsException - Custom exception for when trying to create a resource that already exists
 * Used when attempting to create entities with unique constraints that are already violated
 */
public class ResourceAlreadyExistsException extends RuntimeException {
    
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
    
    public ResourceAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ResourceAlreadyExistsException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s with %s '%s' already exists in the system.", resourceName, fieldName, fieldValue));
    }
} 