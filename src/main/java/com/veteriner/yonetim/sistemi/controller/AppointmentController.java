package com.veteriner.yonetim.sistemi.controller;

import com.veteriner.yonetim.sistemi.dto.AppointmentRequestDto;
import com.veteriner.yonetim.sistemi.dto.AppointmentResponseDto;
import com.veteriner.yonetim.sistemi.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<AppointmentResponseDto> createAppointment(@Valid @RequestBody AppointmentRequestDto requestDto) {
        AppointmentResponseDto responseDto = appointmentService.createAppointment(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDto> getAppointmentById(@PathVariable Long id) {
        AppointmentResponseDto responseDto = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointments() {
        List<AppointmentResponseDto> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/by-doctor/{doctorId}")
    public ResponseEntity<List<AppointmentResponseDto>> getAppointmentsByDoctorId(@PathVariable Long doctorId) {
        List<AppointmentResponseDto> appointments = appointmentService.getAppointmentsByDoctorId(doctorId);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/by-animal/{animalId}")
    public ResponseEntity<List<AppointmentResponseDto>> getAppointmentsByAnimalId(@PathVariable Long animalId) {
        List<AppointmentResponseDto> appointments = appointmentService.getAppointmentsByAnimalId(animalId);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/by-doctor-and-date-range")
    public ResponseEntity<List<AppointmentResponseDto>> getAppointmentsByDoctorIdAndDateRange(
            @RequestParam Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<AppointmentResponseDto> appointments = appointmentService.getAppointmentsByDoctorIdAndDateRange(doctorId, startDate, endDate);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/by-animal-and-date-range")
    public ResponseEntity<List<AppointmentResponseDto>> getAppointmentsByAnimalIdAndDateRange(
            @RequestParam Long animalId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<AppointmentResponseDto> appointments = appointmentService.getAppointmentsByAnimalIdAndDateRange(animalId, startDate, endDate);
        return ResponseEntity.ok(appointments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDto> updateAppointment(@PathVariable Long id, @Valid @RequestBody AppointmentRequestDto requestDto) {
        AppointmentResponseDto responseDto = appointmentService.updateAppointment(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
} 