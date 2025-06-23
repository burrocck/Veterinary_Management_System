package com.veteriner.yonetim.sistemi.service;

import com.veteriner.yonetim.sistemi.dto.CustomerRequestDto;
import com.veteriner.yonetim.sistemi.dto.CustomerResponseDto;
import com.veteriner.yonetim.sistemi.entity.Customer;
import com.veteriner.yonetim.sistemi.exception.ResourceAlreadyExistsException;
import com.veteriner.yonetim.sistemi.exception.ResourceNotFoundException;
import com.veteriner.yonetim.sistemi.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    
    private final CustomerRepository customerRepository;
    
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    
    public CustomerResponseDto createCustomer(CustomerRequestDto requestDto) {
        // Phone check
        if (customerRepository.existsByPhone(requestDto.getPhone())) {
            throw new ResourceAlreadyExistsException("Customer", "phone", requestDto.getPhone());
        }
        
        // Email check (if email exists)
        if (requestDto.getMail() != null && !requestDto.getMail().isEmpty() && customerRepository.existsByMail(requestDto.getMail())) {
            throw new ResourceAlreadyExistsException("Customer", "mail", requestDto.getMail());
        }
        
        Customer customer = new Customer();
        customer.setName(requestDto.getName());
        customer.setPhone(requestDto.getPhone());
        customer.setMail(requestDto.getMail());
        customer.setAddress(requestDto.getAddress());
        customer.setCity(requestDto.getCity());
        
        Customer savedCustomer = customerRepository.save(customer);
        return convertToResponseDto(savedCustomer);
    }
    
    public CustomerResponseDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        return convertToResponseDto(customer);
    }
    
    public List<CustomerResponseDto> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }
    
    public List<CustomerResponseDto> getCustomersByName(String name) {
        return customerRepository.findByNameContainingIgnoreCase(name).stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }
    
    public CustomerResponseDto updateCustomer(Long id, CustomerRequestDto requestDto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        
        // Telefon kontrolü (başka müşteride aynı telefon varsa)
        if (!requestDto.getPhone().equals(customer.getPhone()) && customerRepository.existsByPhone(requestDto.getPhone())) {
            throw new ResourceAlreadyExistsException("Customer", "phone", requestDto.getPhone());
        }
        
        // Email kontrolü (başka müşteride aynı email varsa)
        if (requestDto.getMail() != null && !requestDto.getMail().isEmpty() && 
            !requestDto.getMail().equals(customer.getMail()) && customerRepository.existsByMail(requestDto.getMail())) {
            throw new ResourceAlreadyExistsException("Customer", "mail", requestDto.getMail());
        }
        
        customer.setName(requestDto.getName());
        customer.setPhone(requestDto.getPhone());
        customer.setMail(requestDto.getMail());
        customer.setAddress(requestDto.getAddress());
        customer.setCity(requestDto.getCity());
        
        Customer updatedCustomer = customerRepository.save(customer);
        return convertToResponseDto(updatedCustomer);
    }
    
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Customer", "id", id);
        }
        customerRepository.deleteById(id);
    }
    
    private CustomerResponseDto convertToResponseDto(Customer customer) {
        return new CustomerResponseDto(
                customer.getId(),
                customer.getName(),
                customer.getPhone(),
                customer.getMail(),
                customer.getAddress(),
                customer.getCity()
        );
    }
} 