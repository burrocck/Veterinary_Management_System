package com.veteriner.yonetim.sistemi.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * AvailableDateRequestDto - Data Transfer Object for creating and updating available dates
 * Contains validation annotations for input validation
 */
public class AvailableDateRequestDto {
    
    @NotNull(message = "Date is required")
    private LocalDate date;
    
    @NotNull(message = "Start time is required")
    private LocalTime startTime;
    
    @NotNull(message = "End time is required")
    private LocalTime endTime;
    
    @NotNull(message = "Doctor ID is required")
    private Long doctorId;
    
    // Constructors
    public AvailableDateRequestDto() {}
    
    public AvailableDateRequestDto(LocalDate date, LocalTime startTime, LocalTime endTime, Long doctorId) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.doctorId = doctorId;
    }
    
    // Getters and Setters
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
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
    
    public Long getDoctorId() {
        return doctorId;
    }
    
    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }
} 