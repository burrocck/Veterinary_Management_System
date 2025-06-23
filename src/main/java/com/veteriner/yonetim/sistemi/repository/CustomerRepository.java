package com.veteriner.yonetim.sistemi.repository;

import com.veteriner.yonetim.sistemi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    Optional<Customer> findByName(String name);
    
    List<Customer> findByNameContainingIgnoreCase(String name);
    
    Optional<Customer> findByPhone(String phone);
    
    Optional<Customer> findByMail(String mail);
    
    boolean existsByPhone(String phone);
    
    boolean existsByMail(String mail);
} 