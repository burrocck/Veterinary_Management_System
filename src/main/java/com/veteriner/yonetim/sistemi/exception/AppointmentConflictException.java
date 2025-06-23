package com.veteriner.yonetim.sistemi.exception;

/**
 * AppointmentConflictException - Custom exception for appointment scheduling conflicts
 * Used when trying to schedule an appointment that conflicts with existing appointments
 */
public class AppointmentConflictException extends RuntimeException {
    
    public AppointmentConflictException(String message) {
        super(message);
    }
    
    public AppointmentConflictException(String doctorName, String date, String time) {
        super(String.format("Doctor %s already has another appointment on %s at %s.", doctorName, date, time));
    }
} 