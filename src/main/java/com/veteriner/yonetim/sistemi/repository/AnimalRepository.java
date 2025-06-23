package com.veteriner.yonetim.sistemi.repository;

import com.veteriner.yonetim.sistemi.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    
    Optional<Animal> findByName(String name);
    
    List<Animal> findByNameContainingIgnoreCase(String name);
    
    List<Animal> findByCustomerId(Long customerId);
    
    @Query("SELECT a FROM Animal a WHERE a.customer.name LIKE %:customerName%")
    List<Animal> findByCustomerNameContainingIgnoreCase(@Param("customerName") String customerName);
    
    boolean existsByNameAndCustomerId(String name, Long customerId);
} 