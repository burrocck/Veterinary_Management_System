package com.veteriner.yonetim.sistemi.service;

import com.veteriner.yonetim.sistemi.dto.AppointmentRequestDto;
import com.veteriner.yonetim.sistemi.dto.AppointmentResponseDto;
import com.veteriner.yonetim.sistemi.dto.AnimalResponseDto;
import com.veteriner.yonetim.sistemi.dto.DoctorResponseDto;
import com.veteriner.yonetim.sistemi.entity.Appointment;
import com.veteriner.yonetim.sistemi.entity.Animal;
import com.veteriner.yonetim.sistemi.entity.Doctor;
import com.veteriner.yonetim.sistemi.exception.AppointmentConflictException;
import com.veteriner.yonetim.sistemi.exception.DoctorNotAvailableException;
import com.veteriner.yonetim.sistemi.exception.ResourceNotFoundException;
import com.veteriner.yonetim.sistemi.repository.AppointmentRepository;
import com.veteriner.yonetim.sistemi.repository.AnimalRepository;
import com.veteriner.yonetim.sistemi.repository.DoctorRepository;
import com.veteriner.yonetim.sistemi.repository.AvailableDateRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    
    private final AppointmentRepository appointmentRepository;
    private final AnimalRepository animalRepository;
    private final DoctorRepository doctorRepository;
    private final AvailableDateRepository availableDateRepository;
    
    public AppointmentService(AppointmentRepository appointmentRepository, 
                             AnimalRepository animalRepository, 
                             DoctorRepository doctorRepository,
                             AvailableDateRepository availableDateRepository) {
        this.appointmentRepository = appointmentRepository;
        this.animalRepository = animalRepository;
        this.doctorRepository = doctorRepository;
        this.availableDateRepository = availableDateRepository;
    }
    
    public AppointmentResponseDto createAppointment(AppointmentRequestDto requestDto) {
        // Animal check
        Animal animal = animalRepository.findById(requestDto.getAnimalId())
                .orElseThrow(() -> new ResourceNotFoundException("Animal", "id", requestDto.getAnimalId()));
        
        // Doctor check
        Doctor doctor = doctorRepository.findById(requestDto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", requestDto.getDoctorId()));
        
        LocalDateTime appointmentDate = LocalDateTime.of(
            requestDto.getAppointmentDate(),
            requestDto.getStartTime()
        );
        LocalDate appointmentDateOnly = appointmentDate.toLocalDate();
        LocalTime appointmentTime = appointmentDate.toLocalTime();
        
        // Check if the doctor is available on that date
        if (!availableDateRepository.existsByDoctorIdAndAvailableDate(requestDto.getDoctorId(), appointmentDateOnly)) {
            throw new DoctorNotAvailableException("Doctor " + doctor.getName() + " is not available on " + appointmentDateOnly.toString() + ".");
        }
        
        // Only appointments on the hour are allowed
        if (appointmentTime.getMinute() != 0 || appointmentTime.getSecond() != 0) {
            throw new AppointmentConflictException("Appointments can only be scheduled on the hour.");
        }
        
        // Check if there is another appointment at the same time
        List<Appointment> existingAppointments = appointmentRepository.findByDoctorIdAndAppointmentDate(
                requestDto.getDoctorId(), appointmentDate);
        
        if (!existingAppointments.isEmpty()) {
            throw new AppointmentConflictException(doctor.getName(), appointmentDateOnly.toString(), appointmentTime.toString());
        }
        
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(appointmentDate);
        appointment.setDoctor(doctor);
        appointment.setAnimal(animal);
        
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return convertToResponseDto(savedAppointment);
    }
    
    public AppointmentResponseDto getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", id));
        return convertToResponseDto(appointment);
    }
    
    public List<AppointmentResponseDto> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }
    
    public List<AppointmentResponseDto> getAppointmentsByDoctorId(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId).stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }
    
    public List<AppointmentResponseDto> getAppointmentsByAnimalId(Long animalId) {
        return appointmentRepository.findByAnimalId(animalId).stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }
    
    public List<AppointmentResponseDto> getAppointmentsByDoctorIdAndDateRange(Long doctorId, LocalDate startDate, LocalDate endDate) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);
        return appointmentRepository.findByDoctorIdAndAppointmentDateBetween(doctorId, startDateTime, endDateTime).stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }
    
    public List<AppointmentResponseDto> getAppointmentsByAnimalIdAndDateRange(Long animalId, LocalDate startDate, LocalDate endDate) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);
        return appointmentRepository.findByAnimalIdAndAppointmentDateBetween(animalId, startDateTime, endDateTime).stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }
    
    public AppointmentResponseDto updateAppointment(Long id, AppointmentRequestDto requestDto) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", id));
        
        Animal animal = animalRepository.findById(requestDto.getAnimalId())
                .orElseThrow(() -> new ResourceNotFoundException("Animal", "id", requestDto.getAnimalId()));
        
        Doctor doctor = doctorRepository.findById(requestDto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", requestDto.getDoctorId()));
        
        LocalDateTime appointmentDate = LocalDateTime.of(
            requestDto.getAppointmentDate(),
            requestDto.getStartTime()
        );
        LocalDate appointmentDateOnly = appointmentDate.toLocalDate();
        LocalTime appointmentTime = appointmentDate.toLocalTime();
        
        // Doktorun o tarihte müsait olup olmadığını kontrol et
        if (!availableDateRepository.existsByDoctorIdAndAvailableDate(requestDto.getDoctorId(), appointmentDateOnly)) {
            throw new DoctorNotAvailableException("Doctor " + doctor.getName() + " is not available on " + appointmentDateOnly.toString() + ".");
        }
        
        // Saat başı kontrolü
        if (appointmentTime.getMinute() != 0 || appointmentTime.getSecond() != 0) {
            throw new AppointmentConflictException("Randevu sadece saat başı alınabilir.");
        }
        
        // Aynı saatte başka randevu var mı kontrol et (kendisi hariç)
        List<Appointment> existingAppointments = appointmentRepository.findByDoctorIdAndAppointmentDate(
                requestDto.getDoctorId(), appointmentDate);
        
        if (!existingAppointments.isEmpty() && !existingAppointments.get(0).getId().equals(id)) {
            throw new AppointmentConflictException(doctor.getName(), appointmentDateOnly.toString(), appointmentTime.toString());
        }
        
        appointment.setAppointmentDate(appointmentDate);
        appointment.setDoctor(doctor);
        appointment.setAnimal(animal);
        
        Appointment updatedAppointment = appointmentRepository.save(appointment);
        return convertToResponseDto(updatedAppointment);
    }
    
    public void deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Appointment", "id", id);
        }
        appointmentRepository.deleteById(id);
    }
    
    private AppointmentResponseDto convertToResponseDto(Appointment appointment) {
        DoctorResponseDto doctorDto = new DoctorResponseDto(
                appointment.getDoctor().getId(),
                appointment.getDoctor().getName(),
                appointment.getDoctor().getPhone(),
                appointment.getDoctor().getMail(),
                appointment.getDoctor().getAddress(),
                appointment.getDoctor().getCity()
        );
        
        AnimalResponseDto animalDto = new AnimalResponseDto(
                appointment.getAnimal().getId(),
                appointment.getAnimal().getName(),
                appointment.getAnimal().getSpecies(),
                appointment.getAnimal().getBreed(),
                appointment.getAnimal().getGender(),
                appointment.getAnimal().getColour(),
                appointment.getAnimal().getDateOfBirth(),
                null // Customer information not needed here
        );
        
        return new AppointmentResponseDto(
                appointment.getId(),
                appointment.getAppointmentDate() != null ? appointment.getAppointmentDate().toLocalDate() : null,
                appointment.getAppointmentDate() != null ? appointment.getAppointmentDate().toLocalTime() : null,
                appointment.getEndTime(),
                appointment.getNotes(),
                doctorDto,
                animalDto
        );
    }
} 