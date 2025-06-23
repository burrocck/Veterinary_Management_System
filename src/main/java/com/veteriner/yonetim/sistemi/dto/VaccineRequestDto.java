package com.veteriner.yonetim.sistemi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * VaccineRequestDto - Data Transfer Object for creating and updating vaccines
 * Contains validation annotations for input validation
 */
public class VaccineRequestDto {
    
    @NotBlank(message = "Vaccine name is required")
    private String name;
    
    @NotBlank(message = "Vaccine code is required")
    private String code;
    
    @NotNull(message = "Application date is required")
    private LocalDate applicationDate;
    
    @NotNull(message = "Protection start date is required")
    private LocalDate protectionStartDate;
    
    @NotNull(message = "Protection end date is required")
    private LocalDate protectionEndDate;
    
    @NotNull(message = "Animal ID is required")
    private Long animalId;
    
    // Constructors
    public VaccineRequestDto() {}
    
    public VaccineRequestDto(String name, String code, LocalDate applicationDate, LocalDate protectionStartDate, LocalDate protectionEndDate, Long animalId) {
        this.name = name;
        this.code = code;
        this.applicationDate = applicationDate;
        this.protectionStartDate = protectionStartDate;
        this.protectionEndDate = protectionEndDate;
        this.animalId = animalId;
    }
    
    // Getters and Setters
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
    
    public Long getAnimalId() {
        return animalId;
    }
    
    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }
} 