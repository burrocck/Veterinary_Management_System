package com.veteriner.yonetim.sistemi.service;

import com.veteriner.yonetim.sistemi.dto.AnimalRequestDto;
import com.veteriner.yonetim.sistemi.dto.AnimalResponseDto;
import com.veteriner.yonetim.sistemi.dto.CustomerResponseDto;
import com.veteriner.yonetim.sistemi.entity.Animal;
import com.veteriner.yonetim.sistemi.entity.Customer;
import com.veteriner.yonetim.sistemi.exception.ResourceAlreadyExistsException;
import com.veteriner.yonetim.sistemi.exception.ResourceNotFoundException;
import com.veteriner.yonetim.sistemi.repository.AnimalRepository;
import com.veteriner.yonetim.sistemi.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalService {
    
    private final AnimalRepository animalRepository;
    private final CustomerRepository customerRepository;
    
    public AnimalService(AnimalRepository animalRepository, CustomerRepository customerRepository) {
        this.animalRepository = animalRepository;
        this.customerRepository = customerRepository;
    }
    
    public AnimalResponseDto createAnimal(AnimalRequestDto requestDto) {
        // Customer check
        Customer customer = customerRepository.findById(requestDto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", requestDto.getCustomerId()));
        
        // Check for animal with the same name
        if (animalRepository.existsByNameAndCustomerId(requestDto.getName(), requestDto.getCustomerId())) {
            throw new ResourceAlreadyExistsException("Animal", "name", requestDto.getName());
        }
        
        Animal animal = new Animal();
        animal.setName(requestDto.getName());
        animal.setSpecies(requestDto.getSpecies());
        animal.setBreed(requestDto.getBreed());
        animal.setGender(requestDto.getGender());
        animal.setColour(requestDto.getColour());
        animal.setDateOfBirth(requestDto.getDateOfBirth());
        animal.setCustomer(customer);
        
        Animal savedAnimal = animalRepository.save(animal);
        return convertToResponseDto(savedAnimal);
    }
    
    public AnimalResponseDto getAnimalById(Long id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animal", "id", id));
        return convertToResponseDto(animal);
    }
    
    public List<AnimalResponseDto> getAllAnimals() {
        return animalRepository.findAll().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }
    
    public List<AnimalResponseDto> getAnimalsByName(String name) {
        return animalRepository.findByNameContainingIgnoreCase(name).stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }
    
    public List<AnimalResponseDto> getAnimalsByCustomerId(Long customerId) {
        return animalRepository.findByCustomerId(customerId).stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }
    
    public List<AnimalResponseDto> getAnimalsByCustomerName(String customerName) {
        return animalRepository.findByCustomerNameContainingIgnoreCase(customerName).stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }
    
    public AnimalResponseDto updateAnimal(Long id, AnimalRequestDto requestDto) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animal", "id", id));
        
        Customer customer = customerRepository.findById(requestDto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", requestDto.getCustomerId()));
        
        animal.setName(requestDto.getName());
        animal.setSpecies(requestDto.getSpecies());
        animal.setBreed(requestDto.getBreed());
        animal.setGender(requestDto.getGender());
        animal.setColour(requestDto.getColour());
        animal.setDateOfBirth(requestDto.getDateOfBirth());
        animal.setCustomer(customer);
        
        Animal updatedAnimal = animalRepository.save(animal);
        return convertToResponseDto(updatedAnimal);
    }
    
    public void deleteAnimal(Long id) {
        if (!animalRepository.existsById(id)) {
            throw new ResourceNotFoundException("Animal", "id", id);
        }
        animalRepository.deleteById(id);
    }
    
    private AnimalResponseDto convertToResponseDto(Animal animal) {
        CustomerResponseDto customerDto = new CustomerResponseDto(
                animal.getCustomer().getId(),
                animal.getCustomer().getName(),
                animal.getCustomer().getPhone(),
                animal.getCustomer().getMail(),
                animal.getCustomer().getAddress(),
                animal.getCustomer().getCity()
        );
        
        return new AnimalResponseDto(
                animal.getId(),
                animal.getName(),
                animal.getSpecies(),
                animal.getBreed(),
                animal.getGender(),
                animal.getColour(),
                animal.getDateOfBirth(),
                customerDto
        );
    }
} 