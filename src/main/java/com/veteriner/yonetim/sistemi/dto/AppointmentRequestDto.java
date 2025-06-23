package com.veteriner.yonetim.sistemi.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * AppointmentRequestDto - Data Transfer Object for creating and updating appointments
 * Contains validation annotations for input validation
 */
public class AppointmentRequestDto {
    
    @NotNull(message = "Appointment date is required")
    private LocalDate appointmentDate;
    
    @NotNull(message = "Start time is required")
    private LocalTime startTime;
    
    @NotNull(message = "End time is required")
    private LocalTime endTime;
    
    private String notes;
    
    @NotNull(message = "Doctor ID is required")
    private Long doctorId;
    
    @NotNull(message = "Animal ID is required")
    private Long animalId;
    
    // Constructors
    public AppointmentRequestDto() {}
    
    public AppointmentRequestDto(LocalDate appointmentDate, LocalTime startTime, LocalTime endTime, String notes, Long doctorId, Long animalId) {
        this.appointmentDate = appointmentDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.notes = notes;
        this.doctorId = doctorId;
        this.animalId = animalId;
    }
    
    // Getters and Setters
    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }
    
    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
    
    public LocalTime getStartTime() {
        return startTime;
    }
    
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
    
    public LocalTime getEndTime() {
        return endTime;
    }
    
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public Long getDoctorId() {
        return doctorId;
    }
    
    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }
    
    public Long getAnimalId() {
        return animalId;
    }
    
    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }
} 