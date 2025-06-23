package com.veteriner.yonetim.sistemi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Vaccine Entity - Represents vaccines given to animals
 * Tracks vaccine information including type, application date, and protection period
 */
@Entity
@Table(name = "vaccines")
public class Vaccine {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Vaccine name is required")
    @Column(name = "name", nullable = false)
    private String name;
    
    @NotBlank(message = "Vaccine code is required")
    @Column(name = "code", nullable = false)
    private String code;
    
    @NotNull(message = "Application date is required")
    @Column(name = "application_date", nullable = false)
    private LocalDate applicationDate;
    
    @NotNull(message = "Protection start date is required")
    @Column(name = "protection_start_date", nullable = false)
    private LocalDate protectionStartDate;
    
    @NotNull(message = "Protection end date is required")
    @Column(name = "protection_end_date", nullable = false)
    private LocalDate protectionEndDate;
    
    // Many-to-One relationship with animal
    // Vaccine belongs to a specific animal
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;
    
    // Constructors
    public Vaccine() {}
    
    public Vaccine(String name, String code, LocalDate applicationDate, LocalDate protectionStartDate, LocalDate protectionEndDate, Animal animal) {
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
    
    public Animal getAnimal() {
        return animal;
    }
    
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
    
    @Override
    public String toString() {
        return "Vaccine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", applicationDate=" + applicationDate +
                ", protectionStartDate=" + protectionStartDate +
                ", protectionEndDate=" + protectionEndDate +
                '}';
    }
} 