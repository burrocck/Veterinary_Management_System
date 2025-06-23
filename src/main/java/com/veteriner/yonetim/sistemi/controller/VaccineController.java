package com.veteriner.yonetim.sistemi.controller;

import com.veteriner.yonetim.sistemi.dto.VaccineRequestDto;
import com.veteriner.yonetim.sistemi.dto.VaccineResponseDto;
import com.veteriner.yonetim.sistemi.service.VaccineService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/vaccines")
public class VaccineController {
    private final VaccineService vaccineService;

    public VaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    @PostMapping
    public ResponseEntity<VaccineResponseDto> createVaccine(@Valid @RequestBody VaccineRequestDto requestDto) {
        VaccineResponseDto responseDto = vaccineService.createVaccine(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VaccineResponseDto> getVaccineById(@PathVariable Long id) {
        VaccineResponseDto responseDto = vaccineService.getVaccineById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<VaccineResponseDto>> getAllVaccines() {
        List<VaccineResponseDto> vaccines = vaccineService.getAllVaccines();
        return ResponseEntity.ok(vaccines);
    }

    @GetMapping("/by-animal/{animalId}")
    public ResponseEntity<List<VaccineResponseDto>> getVaccinesByAnimalId(@PathVariable Long animalId) {
        List<VaccineResponseDto> vaccines = vaccineService.getVaccinesByAnimalId(animalId);
        return ResponseEntity.ok(vaccines);
    }

    @GetMapping("/by-protection-finish-date")
    public ResponseEntity<List<VaccineResponseDto>> getVaccinesByProtectionFinishDateBetween(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        List<VaccineResponseDto> vaccines = vaccineService.getVaccinesByProtectionFinishDateBetween(startDate, endDate);
        return ResponseEntity.ok(vaccines);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VaccineResponseDto> updateVaccine(@PathVariable Long id, @Valid @RequestBody VaccineRequestDto requestDto) {
        VaccineResponseDto responseDto = vaccineService.updateVaccine(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVaccine(@PathVariable Long id) {
        vaccineService.deleteVaccine(id);
        return ResponseEntity.noContent().build();
    }
} 