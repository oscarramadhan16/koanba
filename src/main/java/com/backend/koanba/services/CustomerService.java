package com.backend.koanba.services;

import com.backend.koanba.controllers.request.CustomerRequest;
import com.backend.koanba.entities.CustomerEntity;
import com.backend.koanba.exceptions.CustomerNotFoundException;
import com.backend.koanba.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerEntity create(CustomerRequest customerRequest) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(UUID.randomUUID());
        customerEntity.setName(customerRequest.getName());
        customerEntity.setAddress(customerRequest.getAddress());
        customerEntity.setPhone(customerRequest.getPhone());

        return this.customerRepository.save(customerEntity);
    }

    public CustomerEntity update(CustomerRequest customerRequest, String id) throws CustomerNotFoundException {
        CustomerEntity customerEntity = this.customerRepository.findById(UUID.fromString(id)).orElseThrow(() -> new CustomerNotFoundException("customer id not found"));
        customerEntity.setName(customerRequest.getName());
        customerEntity.setAddress(customerRequest.getAddress());
        customerEntity.setPhone(customerRequest.getPhone());

        return this.customerRepository.save(customerEntity);
    }

    public CustomerEntity get(String id) throws CustomerNotFoundException {
        CustomerEntity customerEntity = this.customerRepository.findById(UUID.fromString(id)).orElseThrow(() -> new CustomerNotFoundException("customer id not found"));
        return customerEntity;
    }
}
