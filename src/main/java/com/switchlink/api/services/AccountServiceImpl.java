package com.switchlink.api.services;

import com.switchlink.api.models.Account;
import com.switchlink.api.models.Customer;
import com.switchlink.api.repositories.AccountRepository;
import com.switchlink.api.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    public AccountRepository accountRepository;

    @Autowired
    public CustomerRepository customerRepository;

    public AccountServiceImpl(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Account> getAccount(Long customerId) {

        Customer customer = customerRepository.findByCustomerId(customerId);

        if(customer == null){
            return null;
        }else
        {

            return accountRepository.findByCustomer_CustomerId(customerId);
        }

//        return accountRepository.findByCustomer(1L);
    }
}
