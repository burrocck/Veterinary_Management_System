package com.veteriner.yonetim.sistemi.service;

import com.veteriner.yonetim.sistemi.dto.AnimalResponseDto;
import com.veteriner.yonetim.sistemi.dto.VaccineRequestDto;
import com.veteriner.yonetim.sistemi.dto.VaccineResponseDto;
import com.veteriner.yonetim.sistemi.entity.Animal;
import com.veteriner.yonetim.sistemi.entity.Vaccine;
import com.veteriner.yonetim.sistemi.exception.ResourceAlreadyExistsException;
import com.veteriner.yonetim.sistemi.exception.ResourceNotFoundException;
import com.veteriner.yonetim.sistemi.exception.VaccineProtectionException;
import com.veteriner.yonetim.sistemi.repository.AnimalRepository;
import com.veteriner.yonetim.sistemi.repository.VaccineRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VaccineService {
    
    private final VaccineRepository vaccineRepository;
    private final AnimalRepository animalRepository;
    
    public VaccineService(VaccineRepository vaccineRepository, AnimalRepository animalRepository) {
        this.vaccineRepository = vaccineRepository;
        this.animalRepository = animalRepository;
    }
    
    public VaccineResponseDto createVaccine(VaccineRequestDto requestDto) {
        // Animal check
        Animal animal = animalRepository.findById(requestDto.getAnimalId())
                .orElseThrow(() -> new ResourceNotFoundException("Animal", "id", requestDto.getAnimalId()));
        
        // Vaccine code check
        if (vaccineRepository.existsByCode(requestDto.getCode())) {
            throw new ResourceAlreadyExistsException("Vaccine", "code", requestDto.getCode());
        }
        
        // Check for active protection period for the same vaccine type
        List<Vaccine> activeVaccines = vaccineRepository.findActiveVaccinesByAnimalIdAndName(
                requestDto.getAnimalId(), 
                requestDto.getName(), 
                LocalDate.now()
        );
        
        if (!activeVaccines.isEmpty()) {
            throw new VaccineProtectionException("Animal " + animal.getName() + " already has an active protection for vaccine " + requestDto.getName());
        }
        
        Vaccine vaccine = new Vaccine();
        vaccine.setName(requestDto.getName());
        vaccine.setCode(requestDto.getCode());
        vaccine.setProtectionStartDate(requestDto.getProtectionStartDate());
        vaccine.setProtectionEndDate(requestDto.getProtectionEndDate());
        vaccine.setAnimal(animal);
        
        Vaccine savedVaccine = vaccineRepository.save(vaccine);
        return convertToResponseDto(savedVaccine);
    }
    
    public VaccineResponseDto getVaccineById(Long id) {
        Vaccine vaccine = vaccineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vaccine", "id", id));
        return convertToResponseDto(vaccine);
    }
    
    public List<VaccineResponseDto> getAllVaccines() {
        return vaccineRepository.findAll().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }
    
    public List<VaccineResponseDto> getVaccinesByAnimalId(Long animalId) {
        return vaccineRepository.findByAnimalId(animalId).stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }
    
    public List<VaccineResponseDto> getVaccinesByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate) {
        return vaccineRepository.findVaccinesByProtectionFinishDateBetween(startDate, endDate).stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }
    
    public VaccineResponseDto updateVaccine(Long id, VaccineRequestDto requestDto) {
        Vaccine vaccine = vaccineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vaccine", "id", id));
        
        Animal animal = animalRepository.findById(requestDto.getAnimalId())
                .orElseThrow(() -> new ResourceNotFoundException("Animal", "id", requestDto.getAnimalId()));
        
        vaccine.setName(requestDto.getName());
        vaccine.setCode(requestDto.getCode());
        vaccine.setProtectionStartDate(requestDto.getProtectionStartDate());
        vaccine.setProtectionEndDate(requestDto.getProtectionEndDate());
        vaccine.setAnimal(animal);
        
        Vaccine updatedVaccine = vaccineRepository.save(vaccine);
        return convertToResponseDto(updatedVaccine);
    }
    
    public void deleteVaccine(Long id) {
        if (!vaccineRepository.existsById(id)) {
            throw new ResourceNotFoundException("Vaccine", "id", id);
        }
        vaccineRepository.deleteById(id);
    }
    
    private VaccineResponseDto convertToResponseDto(Vaccine vaccine) {
        AnimalResponseDto animalDto = new AnimalResponseDto(
                vaccine.getAnimal().getId(),
                vaccine.getAnimal().getName(),
                vaccine.getAnimal().getSpecies(),
                vaccine.getAnimal().getBreed(),
                vaccine.getAnimal().getGender(),
                vaccine.getAnimal().getColour(),
                vaccine.getAnimal().getDateOfBirth(),
                null // Customer information not needed here
        );
        
        return new VaccineResponseDto(
                vaccine.getId(),
                vaccine.getName(),
                vaccine.getCode(),
                vaccine.getApplicationDate(),
                vaccine.getProtectionStartDate(),
                vaccine.getProtectionEndDate(),
                animalDto
        );
    }
} 