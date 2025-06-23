package com.veteriner.yonetim.sistemi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * GlobalExceptionHandler - Centralized exception handling for the application
 * Provides consistent error responses across all REST endpoints
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    /**
     * Handles ResourceNotFoundException and returns 404 status
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMap);
    }
    
    /**
     * Handles ResourceAlreadyExistsException and returns 409 status
     */
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMap);
    }
    
    /**
     * Handles AppointmentConflictException and returns 409 status
     */
    @ExceptionHandler(AppointmentConflictException.class)
    public ResponseEntity<Map<String, String>> handleAppointmentConflictException(AppointmentConflictException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMap);
    }
    
    /**
     * Handles DoctorNotAvailableException and returns 400 status
     */
    @ExceptionHandler(DoctorNotAvailableException.class)
    public ResponseEntity<Map<String, String>> handleDoctorNotAvailableException(DoctorNotAvailableException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }
    
    /**
     * Handles VaccineProtectionException and returns 400 status
     */
    @ExceptionHandler(VaccineProtectionException.class)
    public ResponseEntity<Map<String, String>> handleVaccineProtectionException(VaccineProtectionException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }
    
    /**
     * Handles validation errors and returns 400 status with field-specific errors
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
    
    /**
     * Handles general exceptions and returns 500 status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", "An unexpected error occurred: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMap);
    }
} 