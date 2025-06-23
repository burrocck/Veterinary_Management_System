package com.veteriner.yonetim.sistemi.dto;

import java.time.LocalDate;

/**
 * VaccineResponseDto - Data Transfer Object for vaccine responses
 * Contains vaccine information and associated animal details
 */
public class VaccineResponseDto {
    
    private Long id;
    private String name;
    private String code;
    private LocalDate applicationDate;
    private LocalDate protectionStartDate;
    private LocalDate protectionEndDate;
    private AnimalResponseDto animal;
    
    // Constructors
    public VaccineResponseDto() {}
    
    public VaccineResponseDto(Long id, String name, String code, LocalDate applicationDate, LocalDate protectionStartDate, LocalDate protectionEndDate, AnimalResponseDto animal) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.applicationDate = applicationDate;
        this.protectionStartDate = protectionStartDate;
        this.protectionEndDate = protectionEndDate;
        this.animal = animal;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public LocalDate getApplicationDate() {
        return applicationDate;
    }
    
    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }
    
    public LocalDate getProtectionStartDate() {
        return protectionStartDate;
    }
    
    public void setProtectionStartDate(LocalDate protectionStartDate) {
        this.protectionStartDate = protectionStartDate;
    }
    
    public LocalDate getProtectionEndDate() {
        return protectionEndDate;
    }
    
    public void setProtectionEndDate(LocalDate protectionEndDate) {
        this.protectionEndDate = protectionEndDate;
    }
    
    public AnimalResponseDto getAnimal() {
        return animal;
    }
    
    public void setAnimal(AnimalResponseDto animal) {
        this.animal = animal;
    }
} 