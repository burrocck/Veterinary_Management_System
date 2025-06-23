package com.veteriner.yonetim.sistemi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * DoctorRequestDto - Data Transfer Object for creating and updating doctors
 * Contains validation annotations for input validation
 */
public class DoctorRequestDto {
    
    @NotBlank(message = "Doctor name is required")
    private String name;
    
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{11}$", message = "Phone number must be 11 digits")
    private String phone;
    
    @Email(message = "Invalid email format")
    private String mail;
    
    private String address;
    
    private String city;
    
    // Constructors
    public DoctorRequestDto() {}
    
    public DoctorRequestDto(String name, String phone, String mail, String address, String city) {
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.address = address;
        this.city = city;
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getMail() {
        return mail;
    }
    
    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
} 