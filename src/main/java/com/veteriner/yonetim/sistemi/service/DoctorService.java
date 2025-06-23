package com.veteriner.yonetim.sistemi.service;

import com.veteriner.yonetim.sistemi.dto.DoctorRequestDto;
import com.veteriner.yonetim.sistemi.dto.DoctorResponseDto;
import com.veteriner.yonetim.sistemi.entity.Doctor;
import com.veteriner.yonetim.sistemi.exception.ResourceAlreadyExistsException;
import com.veteriner.yonetim.sistemi.exception.ResourceNotFoundException;
import com.veteriner.yonetim.sistemi.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {
    
    private final DoctorRepository doctorRepository;
    
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
    
    public DoctorResponseDto createDoctor(DoctorRequestDto requestDto) {
        // Phone check
        if (doctorRepository.existsByPhone(requestDto.getPhone())) {
            throw new ResourceAlreadyExistsException("Doctor", "phone", requestDto.getPhone());
        }
        
        // Email check (if email exists)
        if (requestDto.getMail() != null && !requestDto.getMail().isEmpty() && doctorRepository.existsByMail(requestDto.getMail())) {
            throw new ResourceAlreadyExistsException("Doctor", "mail", requestDto.getMail());
        }
        
        Doctor doctor = new Doctor();
        doctor.setName(requestDto.getName());
        doctor.setPhone(requestDto.getPhone());
        doctor.setMail(requestDto.getMail());
        doctor.setAddress(requestDto.getAddress());
        doctor.setCity(requestDto.getCity());
        
        Doctor savedDoctor = doctorRepository.save(doctor);
        return convertToResponseDto(savedDoctor);
    }
    
    public DoctorResponseDto getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", id));
        return convertToResponseDto(doctor);
    }
    
    public List<DoctorResponseDto> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }
    
    public List<DoctorResponseDto> getDoctorsByName(String name) {
        return doctorRepository.findByNameContainingIgnoreCase(name).stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }
    
    public DoctorResponseDto updateDoctor(Long id, DoctorRequestDto requestDto) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", id));
        
        // Telefon kontrolü (başka doktorda aynı telefon varsa)
        if (!requestDto.getPhone().equals(doctor.getPhone()) && doctorRepository.existsByPhone(requestDto.getPhone())) {
            throw new ResourceAlreadyExistsException("Doctor", "phone", requestDto.getPhone());
        }
        
        // Email kontrolü (başka doktorda aynı email varsa)
        if (requestDto.getMail() != null && !requestDto.getMail().isEmpty() && 
            !requestDto.getMail().equals(doctor.getMail()) && doctorRepository.existsByMail(requestDto.getMail())) {
            throw new ResourceAlreadyExistsException("Doctor", "mail", requestDto.getMail());
        }
        
        doctor.setName(requestDto.getName());
        doctor.setPhone(requestDto.getPhone());
        doctor.setMail(requestDto.getMail());
        doctor.setAddress(requestDto.getAddress());
        doctor.setCity(requestDto.getCity());
        
        Doctor updatedDoctor = doctorRepository.save(doctor);
        return convertToResponseDto(updatedDoctor);
    }
    
    public void deleteDoctor(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Doctor", "id", id);
        }
        doctorRepository.deleteById(id);
    }
    
    private DoctorResponseDto convertToResponseDto(Doctor doctor) {
        return new DoctorResponseDto(
                doctor.getId(),
                doctor.getName(),
                doctor.getPhone(),
                doctor.getMail(),
                doctor.getAddress(),
                doctor.getCity()
        );
    }
} 