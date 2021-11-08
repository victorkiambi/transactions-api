package com.switchlink.api.services;

import com.switchlink.api.models.Account;
import com.switchlink.api.models.Customer;

import java.util.List;

public interface CustomerService {
    Customer save(Customer newCustomer);

    Customer getCustomer(Long customerId);
}
