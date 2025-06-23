package com.veteriner.yonetim.sistemi.dto;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * AvailableDateResponseDto - Data Transfer Object for available date responses
 * Contains available date information and associated doctor details
 */
public class AvailableDateResponseDto {
    
    private Long id;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private DoctorResponseDto doctor;
    
    // Constructors
    public AvailableDateResponseDto() {}
    
    public AvailableDateResponseDto(Long id, LocalDate date, LocalTime startTime, LocalTime endTime, DoctorResponseDto doctor) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.doctor = doctor;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
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
    
    public DoctorResponseDto getDoctor() {
        return doctor;
    }
    
    public void setDoctor(DoctorResponseDto doctor) {
        this.doctor = doctor;
    }
} 