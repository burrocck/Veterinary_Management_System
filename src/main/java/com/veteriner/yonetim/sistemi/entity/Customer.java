package com.veteriner.yonetim.sistemi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.List;

/**
 * Customer Entity - Represents veterinary clinic customers
 * Stores customer information including personal details and contact information
 */
@Entity
@Table(name = "customers")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Customer name is required")
    @Column(name = "name", nullable = false)
    private String name;
    
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{11}$", message = "Phone number must be 11 digits")
    @Column(name = "phone", nullable = false, unique = true)
    private String phone;
    
    @Email(message = "Invalid email format")
    @Column(name = "mail")
    private String mail;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "city")
    private String city;
    
    // One-to-Many relationship with animals
    // When customer is deleted, all their animals are also deleted
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Animal> animals;
    
    // Constructors
    public Customer() {}
    
    public Customer(String name, String phone, String mail, String address, String city) {
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.address = address;
        this.city = city;
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
    
    public List<Animal> getAnimals() {
        return animals;
    }
    
    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }
    
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", mail='" + mail + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
} 