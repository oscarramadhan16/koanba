package com.backend.koanba.controllers;

import com.backend.koanba.controllers.request.CustomerRequest;
import com.backend.koanba.entities.CustomerEntity;
import com.backend.koanba.exceptions.CustomerNotFoundException;
import com.backend.koanba.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //  add
    @PostMapping
    public ResponseEntity<CustomerEntity> create(
            @RequestBody CustomerRequest customerRequest
    ) {
        return ResponseEntity.ok(this.customerService.create(customerRequest));
    }

    //  update
    @PutMapping("/{id}")
    public ResponseEntity<CustomerEntity> update(
            @RequestBody CustomerRequest customerRequest,
            @PathVariable String id
    ) throws CustomerNotFoundException {
        CustomerEntity customerEntity = this.customerService.update(customerRequest, id);
        return ResponseEntity.ok(customerEntity);
    }

    //  get
    @GetMapping("/{id}")
    public ResponseEntity<CustomerEntity> get(@PathVariable String id) throws CustomerNotFoundException {
        CustomerEntity customerEntity = this.customerService.get(id);
        return ResponseEntity.ok(customerEntity);
    }
}
