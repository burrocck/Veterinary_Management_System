package com.veteriner.yonetim.sistemi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Veterinary Management System Application
 * 
 * Main Spring Boot application class for the Veterinary Management System.
 * This application provides RESTful APIs for managing:
 * - Customers and their animals
 * - Veterinary doctors and their availability
 * - Vaccines and protection periods
 * - Appointment scheduling with conflict detection
 * 
 * Features:
 * - Layered architecture (Controller, Service, Repository, Entity)
 * - PostgreSQL database integration
 * - Input validation and error handling
 * - Business logic constraints (no overlapping appointments, vaccine protection periods)
 * 
 * @author Veterinary Management System Team
 * @version 1.0
 */
@SpringBootApplication
public class VeterinerYonetimSistemiApplication {
    
    /**
     * Main method to start the Spring Boot application
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(VeterinerYonetimSistemiApplication.class, args);
        System.out.println("Veterinary Management System is running on http://localhost:8080");
    }
} 