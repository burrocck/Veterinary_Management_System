package com.veteriner.yonetim.sistemi.repository;

import com.veteriner.yonetim.sistemi.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    
    List<Appointment> findByDoctorId(Long doctorId);
    
    List<Appointment> findByAnimalId(Long animalId);
    
    @Query("SELECT a FROM Appointment a WHERE a.doctor.id = :doctorId AND a.appointmentDate BETWEEN :startDate AND :endDate")
    List<Appointment> findByDoctorIdAndAppointmentDateBetween(@Param("doctorId") Long doctorId, 
                                                              @Param("startDate") LocalDateTime startDate, 
                                                              @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT a FROM Appointment a WHERE a.animal.id = :animalId AND a.appointmentDate BETWEEN :startDate AND :endDate")
    List<Appointment> findByAnimalIdAndAppointmentDateBetween(@Param("animalId") Long animalId, 
                                                              @Param("startDate") LocalDateTime startDate, 
                                                              @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT a FROM Appointment a WHERE a.doctor.id = :doctorId AND a.appointmentDate = :appointmentDate")
    List<Appointment> findByDoctorIdAndAppointmentDate(@Param("doctorId") Long doctorId, 
                                                       @Param("appointmentDate") LocalDateTime appointmentDate);
} 