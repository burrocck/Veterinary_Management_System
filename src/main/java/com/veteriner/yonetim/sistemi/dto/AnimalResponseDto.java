package com.veteriner.yonetim.sistemi.dto;

import java.time.LocalDate;
import java.util.List;

/**
 * AnimalResponseDto - Data Transfer Object for animal responses
 * Contains animal information and list of associated vaccines
 */
public class AnimalResponseDto {
    
    private Long id;
    private String name;
    private String species;
    private String breed;
    private String gender;
    private String colour;
    private LocalDate dateOfBirth;
    private CustomerResponseDto customer;
    private List<VaccineResponseDto> vaccines;
    
    // Constructors
    public AnimalResponseDto() {}
    
    public AnimalResponseDto(Long id, String name, String species, String breed, String gender, String colour, LocalDate dateOfBirth, CustomerResponseDto customer, List<VaccineResponseDto> vaccines) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.gender = gender;
        this.colour = colour;
        this.dateOfBirth = dateOfBirth;
        this.customer = customer;
        this.vaccines = vaccines;
    }
    
    public AnimalResponseDto(Long id, String name, String species, String breed, String gender, String colour, LocalDate dateOfBirth, CustomerResponseDto customer) {
        this(id, name, species, breed, gender, colour, dateOfBirth, customer, null);
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
    
    public CustomerResponseDto getCustomer() {
        return customer;
    }
    
    public void setCustomer(CustomerResponseDto customer) {
        this.customer = customer;
    }
    
    public List<VaccineResponseDto> getVaccines() {
        return vaccines;
    }
    
    public void setVaccines(List<VaccineResponseDto> vaccines) {
        this.vaccines = vaccines;
    }
} 