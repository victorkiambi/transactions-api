package com.switchlink.api.controllers;

import com.switchlink.api.models.Customer;
import com.switchlink.api.repositories.CustomerRepository;
import com.switchlink.api.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @Autowired
    public CustomerRepository customerRepository;

    public CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(path = "/api/v1/customers")
    public ResponseEntity<Object> create(@RequestBody Customer newCustomer) {
        return customerService.save(newCustomer);
    }

    @GetMapping("/api/v1/customer/{customerId}")
    public ResponseEntity<Object> getAccount(@PathVariable Long customerId) {
        return customerService.getCustomer(customerId);
    }
}
