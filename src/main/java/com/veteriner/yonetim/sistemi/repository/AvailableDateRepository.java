package com.veteriner.yonetim.sistemi.repository;

import com.veteriner.yonetim.sistemi.entity.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AvailableDateRepository extends JpaRepository<AvailableDate, Long> {
    
    List<AvailableDate> findByDoctorId(Long doctorId);
    
    Optional<AvailableDate> findByDoctorIdAndAvailableDate(Long doctorId, LocalDate availableDate);
    
    boolean existsByDoctorIdAndAvailableDate(Long doctorId, LocalDate availableDate);
} 