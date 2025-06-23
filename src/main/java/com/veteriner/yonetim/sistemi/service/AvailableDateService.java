package com.veteriner.yonetim.sistemi.service;

import com.veteriner.yonetim.sistemi.dto.AvailableDateRequestDto;
import com.veteriner.yonetim.sistemi.dto.AvailableDateResponseDto;
import com.veteriner.yonetim.sistemi.dto.DoctorResponseDto;
import com.veteriner.yonetim.sistemi.entity.AvailableDate;
import com.veteriner.yonetim.sistemi.entity.Doctor;
import com.veteriner.yonetim.sistemi.exception.ResourceAlreadyExistsException;
import com.veteriner.yonetim.sistemi.exception.ResourceNotFoundException;
import com.veteriner.yonetim.sistemi.repository.AvailableDateRepository;
import com.veteriner.yonetim.sistemi.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvailableDateService {
    
    private final AvailableDateRepository availableDateRepository;
    private final DoctorRepository doctorRepository;
    
    public AvailableDateService(AvailableDateRepository availableDateRepository, DoctorRepository doctorRepository) {
        this.availableDateRepository = availableDateRepository;
        this.doctorRepository = doctorRepository;
    }
    
    public AvailableDateResponseDto createAvailableDate(AvailableDateRequestDto requestDto) {
        // Doctor check
        Doctor doctor = doctorRepository.findById(requestDto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", requestDto.getDoctorId()));
        
        // Check for duplicate date
        if (availableDateRepository.existsByDoctorIdAndAvailableDate(requestDto.getDoctorId(), requestDto.getDate())) {
            throw new ResourceAlreadyExistsException("AvailableDate", "date", requestDto.getDate());
        }
        
        AvailableDate availableDate = new AvailableDate();
        availableDate.setDate(requestDto.getDate());
        availableDate.setDoctor(doctor);
        
        AvailableDate savedAvailableDate = availableDateRepository.save(availableDate);
        return convertToResponseDto(savedAvailableDate);
    }
    
    public AvailableDateResponseDto getAvailableDateById(Long id) {
        AvailableDate availableDate = availableDateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AvailableDate", "id", id));
        return convertToResponseDto(availableDate);
    }
    
    public List<AvailableDateResponseDto> getAllAvailableDates() {
        return availableDateRepository.findAll().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }
    
    public List<AvailableDateResponseDto> getAvailableDatesByDoctorId(Long doctorId) {
        return availableDateRepository.findByDoctorId(doctorId).stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }
    
    public AvailableDateResponseDto updateAvailableDate(Long id, AvailableDateRequestDto requestDto) {
        AvailableDate availableDate = availableDateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AvailableDate", "id", id));
        
        Doctor doctor = doctorRepository.findById(requestDto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", requestDto.getDoctorId()));
        
        availableDate.setDate(requestDto.getDate());
        availableDate.setDoctor(doctor);
        
        AvailableDate updatedAvailableDate = availableDateRepository.save(availableDate);
        return convertToResponseDto(updatedAvailableDate);
    }
    
    public void deleteAvailableDate(Long id) {
        if (!availableDateRepository.existsById(id)) {
            throw new ResourceNotFoundException("AvailableDate", "id", id);
        }
        availableDateRepository.deleteById(id);
    }
    
    private AvailableDateResponseDto convertToResponseDto(AvailableDate availableDate) {
        DoctorResponseDto doctorDto = new DoctorResponseDto(
                availableDate.getDoctor().getId(),
                availableDate.getDoctor().getName(),
                availableDate.getDoctor().getPhone(),
                availableDate.getDoctor().getMail(),
                availableDate.getDoctor().getAddress(),
                availableDate.getDoctor().getCity()
        );
        
        return new AvailableDateResponseDto(
                availableDate.getId(),
                availableDate.getDate(),
                availableDate.getStartTime(),
                availableDate.getEndTime(),
                doctorDto
        );
    }
} 