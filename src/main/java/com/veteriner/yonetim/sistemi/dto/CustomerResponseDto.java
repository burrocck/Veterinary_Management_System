package com.veteriner.yonetim.sistemi.dto;

import java.util.List;

/**
 * CustomerResponseDto - Data Transfer Object for customer responses
 * Contains customer information and list of associated animals
 */
public class CustomerResponseDto {
    
    private Long id;
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;
    private List<AnimalResponseDto> animals;
    
    // Constructors
    public CustomerResponseDto() {}
    
    public CustomerResponseDto(Long id, String name, String phone, String mail, String address, String city, List<AnimalResponseDto> animals) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.address = address;
        this.city = city;
        this.animals = animals;
    }
    
    public CustomerResponseDto(Long id, String name, String phone, String mail, String address, String city) {
        this(id, name, phone, mail, address, city, null);
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
    
    public List<AnimalResponseDto> getAnimals() {
        return animals;
    }
    
    public void setAnimals(List<AnimalResponseDto> animals) {
        this.animals = animals;
    }
} 