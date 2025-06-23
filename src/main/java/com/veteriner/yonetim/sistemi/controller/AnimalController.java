package com.veteriner.yonetim.sistemi.controller;

import com.veteriner.yonetim.sistemi.dto.AnimalRequestDto;
import com.veteriner.yonetim.sistemi.dto.AnimalResponseDto;
import com.veteriner.yonetim.sistemi.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    public ResponseEntity<AnimalResponseDto> createAnimal(@Valid @RequestBody AnimalRequestDto requestDto) {
        AnimalResponseDto responseDto = animalService.createAnimal(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponseDto> getAnimalById(@PathVariable Long id) {
        AnimalResponseDto responseDto = animalService.getAnimalById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<AnimalResponseDto>> getAllAnimals() {
        List<AnimalResponseDto> animals = animalService.getAllAnimals();
        return ResponseEntity.ok(animals);
    }

    @GetMapping("/search")
    public ResponseEntity<List<AnimalResponseDto>> getAnimalsByName(@RequestParam String name) {
        List<AnimalResponseDto> animals = animalService.getAnimalsByName(name);
        return ResponseEntity.ok(animals);
    }

    @GetMapping("/by-customer/{customerId}")
    public ResponseEntity<List<AnimalResponseDto>> getAnimalsByCustomerId(@PathVariable Long customerId) {
        List<AnimalResponseDto> animals = animalService.getAnimalsByCustomerId(customerId);
        return ResponseEntity.ok(animals);
    }

    @GetMapping("/by-customer-name")
    public ResponseEntity<List<AnimalResponseDto>> getAnimalsByCustomerName(@RequestParam String customerName) {
        List<AnimalResponseDto> animals = animalService.getAnimalsByCustomerName(customerName);
        return ResponseEntity.ok(animals);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalResponseDto> updateAnimal(@PathVariable Long id, @Valid @RequestBody AnimalRequestDto requestDto) {
        AnimalResponseDto responseDto = animalService.updateAnimal(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Long id) {
        animalService.deleteAnimal(id);
        return ResponseEntity.noContent().build();
    }
} 