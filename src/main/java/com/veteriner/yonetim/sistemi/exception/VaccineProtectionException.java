package com.veteriner.yonetim.sistemi.exception;

/**
 * VaccineProtectionException - Custom exception for vaccine protection period conflicts
 * Used when trying to schedule an appointment during vaccine protection period
 */
public class VaccineProtectionException extends RuntimeException {
    
    public VaccineProtectionException(String message) {
        super(message);
    }
    
    public VaccineProtectionException(String animalName, String vaccineName, String startDate, String endDate) {
        super(String.format("Animal %s has active vaccine protection (%s) from %s to %s. Appointment cannot be scheduled during this period.", animalName, vaccineName, startDate, endDate));
    }
} 