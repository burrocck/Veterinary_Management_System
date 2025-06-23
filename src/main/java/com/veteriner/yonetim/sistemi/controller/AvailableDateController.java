package com.veteriner.yonetim.sistemi.controller;

import com.veteriner.yonetim.sistemi.dto.AvailableDateRequestDto;
import com.veteriner.yonetim.sistemi.dto.AvailableDateResponseDto;
import com.veteriner.yonetim.sistemi.service.AvailableDateService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/available-dates")
public class AvailableDateController {
    private final AvailableDateService availableDateService;

    public AvailableDateController(AvailableDateService availableDateService) {
        this.availableDateService = availableDateService;
    }

    @PostMapping
    public ResponseEntity<AvailableDateResponseDto> createAvailableDate(@Valid @RequestBody AvailableDateRequestDto requestDto) {
        AvailableDateResponseDto responseDto = availableDateService.createAvailableDate(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvailableDateResponseDto> getAvailableDateById(@PathVariable Long id) {
        AvailableDateResponseDto responseDto = availableDateService.getAvailableDateById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<AvailableDateResponseDto>> getAllAvailableDates() {
        List<AvailableDateResponseDto> availableDates = availableDateService.getAllAvailableDates();
        return ResponseEntity.ok(availableDates);
    }

    @GetMapping("/by-doctor/{doctorId}")
    public ResponseEntity<List<AvailableDateResponseDto>> getAvailableDatesByDoctorId(@PathVariable Long doctorId) {
        List<AvailableDateResponseDto> availableDates = availableDateService.getAvailableDatesByDoctorId(doctorId);
        return ResponseEntity.ok(availableDates);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvailableDateResponseDto> updateAvailableDate(@PathVariable Long id, @Valid @RequestBody AvailableDateRequestDto requestDto) {
        AvailableDateResponseDto responseDto = availableDateService.updateAvailableDate(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvailableDate(@PathVariable Long id) {
        availableDateService.deleteAvailableDate(id);
        return ResponseEntity.noContent().build();
    }
} 