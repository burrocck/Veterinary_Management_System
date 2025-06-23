package com.veteriner.yonetim.sistemi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.List;

/**
 * Doctor Entity - Represents veterinary doctors
 * Stores doctor information including contact details and professional information
 */
@Entity
@Table(name = "doctors")
public class Doctor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Doctor name is required")
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
    
    // One-to-Many relationship with available dates
    // When doctor is deleted, all their available dates are also deleted
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AvailableDate> availableDates;
    
    // One-to-Many relationship with appointments
    // When doctor is deleted, all their appointments are also deleted
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointments;
    
    // Constructors
    public Doctor() {}
    
    public Doctor(String name, String phone, String mail, String address, String city) {
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
    
    public List<AvailableDate> getAvailableDates() {
        return availableDates;
    }
    
    public void setAvailableDates(List<AvailableDate> availableDates) {
        this.availableDates = availableDates;
    }
    
    public List<Appointment> getAppointments() {
        return appointments;
    }
    
    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
    
    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", mail='" + mail + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
} 