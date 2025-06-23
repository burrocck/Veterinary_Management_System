package com.veteriner.yonetim.sistemi.repository;

import com.veteriner.yonetim.sistemi.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
    
    List<Vaccine> findByAnimalId(Long animalId);
    
    Optional<Vaccine> findByCode(String code);
    
    boolean existsByCode(String code);
    
    @Query("SELECT v FROM Vaccine v WHERE v.animal.id = :animalId AND v.name = :vaccineName AND v.protectionFinishDate > :currentDate")
    List<Vaccine> findActiveVaccinesByAnimalIdAndName(@Param("animalId") Long animalId, 
                                                      @Param("vaccineName") String vaccineName, 
                                                      @Param("currentDate") LocalDate currentDate);
    
    @Query("SELECT v FROM Vaccine v WHERE v.protectionFinishDate BETWEEN :startDate AND :endDate")
    List<Vaccine> findVaccinesByProtectionFinishDateBetween(@Param("startDate") LocalDate startDate, 
                                                            @Param("endDate") LocalDate endDate);
} 