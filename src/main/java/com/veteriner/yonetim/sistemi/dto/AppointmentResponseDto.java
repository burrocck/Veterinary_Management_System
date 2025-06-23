package com.veteriner.yonetim.sistemi.dto;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * AppointmentResponseDto - Data Transfer Object for appointment responses
 * Contains appointment information and associated doctor and animal details
 */
public class AppointmentResponseDto {
    
    private Long id;
    private LocalDate appointmentDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String notes;
    private DoctorResponseDto doctor;
    private AnimalResponseDto animal;
    
    // Constructors
    public AppointmentResponseDto() {}
    
    public AppointmentResponseDto(Long id, LocalDate appointmentDate, LocalTime startTime, LocalTime endTime, String notes, DoctorResponseDto doctor, AnimalResponseDto animal) {
        this.id = id;
        this.appointmentDate = appointmentDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.notes = notes;
        this.doctor = doctor;
        this.animal = animal;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
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
    
    public DoctorResponseDto getDoctor() {
        return doctor;
    }
    
    public void setDoctor(DoctorResponseDto doctor) {
        this.doctor = doctor;
    }
    
    public AnimalResponseDto getAnimal() {
        return animal;
    }
    
    public void setAnimal(AnimalResponseDto animal) {
        this.animal = animal;
    }
} 