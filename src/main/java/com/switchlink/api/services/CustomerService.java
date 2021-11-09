package com.switchlink.api.services;

import com.switchlink.api.models.Customer;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    ResponseEntity<Object> save(Customer newCustomer);

    ResponseEntity<Object> getCustomer(Long customerId);
}
