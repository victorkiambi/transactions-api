package com.switchlink.api.repositories;

import com.switchlink.api.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByCustomerId(Long customerId);
}
