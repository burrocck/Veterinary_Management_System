package com.veteriner.yonetim.sistemi.controller;

import com.veteriner.yonetim.sistemi.dto.DoctorRequestDto;
import com.veteriner.yonetim.sistemi.dto.DoctorResponseDto;
import com.veteriner.yonetim.sistemi.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<DoctorResponseDto> createDoctor(@Valid @RequestBody DoctorRequestDto requestDto) {
        DoctorResponseDto responseDto = doctorService.createDoctor(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDto> getDoctorById(@PathVariable Long id) {
        DoctorResponseDto responseDto = doctorService.getDoctorById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponseDto>> getAllDoctors() {
        List<DoctorResponseDto> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/search")
    public ResponseEntity<List<DoctorResponseDto>> getDoctorsByName(@RequestParam String name) {
        List<DoctorResponseDto> doctors = doctorService.getDoctorsByName(name);
        return ResponseEntity.ok(doctors);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDto> updateDoctor(@PathVariable Long id, @Valid @RequestBody DoctorRequestDto requestDto) {
        DoctorResponseDto responseDto = doctorService.updateDoctor(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
} 