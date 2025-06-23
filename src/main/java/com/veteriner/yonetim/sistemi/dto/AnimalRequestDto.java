package com.veteriner.yonetim.sistemi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * AnimalRequestDto - Data Transfer Object for creating and updating animals
 * Contains validation annotations for input validation
 */
public class AnimalRequestDto {
    
    @NotBlank(message = "Animal name is required")
    private String name;
    
    @NotBlank(message = "Species is required")
    private String species;
    
    private String breed;
    
    private String gender;
    
    private String colour;
    
    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;
    
    @NotNull(message = "Customer ID is required")
    private Long customerId;
    
    // Constructors
    public AnimalRequestDto() {}
    
    public AnimalRequestDto(String name, String species, String breed, String gender, String colour, LocalDate dateOfBirth, Long customerId) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.gender = gender;
        this.colour = colour;
        this.dateOfBirth = dateOfBirth;
        this.customerId = customerId;
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSpecies() {
        return species;
    }
    
    public void setSpecies(String species) {
        this.species = species;
    }
    
    public String getBreed() {
        return breed;
    }
    
    public void setBreed(String breed) {
        this.breed = breed;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getColour() {
        return colour;
    }
    
    public void setColour(String colour) {
        this.colour = colour;
    }
    
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public Long getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
} 