package com.veteriner.yonetim.sistemi.repository;

import com.veteriner.yonetim.sistemi.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    
    Optional<Doctor> findByName(String name);
    
    List<Doctor> findByNameContainingIgnoreCase(String name);
    
    Optional<Doctor> findByPhone(String phone);
    
    Optional<Doctor> findByMail(String mail);
    
    boolean existsByPhone(String phone);
    
    boolean existsByMail(String mail);
} 