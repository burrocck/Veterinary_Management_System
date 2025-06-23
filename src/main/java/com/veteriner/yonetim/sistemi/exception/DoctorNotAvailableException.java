package com.veteriner.yonetim.sistemi.exception;

/**
 * DoctorNotAvailableException - Custom exception for when doctor is not available
 * Used when trying to schedule an appointment outside doctor's available hours
 */
public class DoctorNotAvailableException extends RuntimeException {
    
    public DoctorNotAvailableException(String message) {
        super(message);
    }
    
    public DoctorNotAvailableException(String doctorName, String date, String time) {
        super(String.format("Doctor %s is not available on %s at %s.", doctorName, date, time));
    }
} 