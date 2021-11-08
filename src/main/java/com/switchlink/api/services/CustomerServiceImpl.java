package com.switchlink.api.services;

import com.switchlink.api.models.Account;
import com.switchlink.api.models.Customer;
import com.switchlink.api.models.Transaction;
import com.switchlink.api.models.TransactionType;
import com.switchlink.api.repositories.AccountRepository;
import com.switchlink.api.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    public CustomerRepository customerRepository;

    @Autowired
    public AccountRepository accountRepository;

    @Override
    public Customer save(Customer newCustomer) {
        Customer customer = new Customer();
        customer.setCustomerName(newCustomer.getCustomerName());
        customer.setCustomerEmail(newCustomer.getCustomerEmail());
        customer.setCustomerPhone(newCustomer.getCustomerPhone());

        Account account = new Account();
        account.setTransactionType(TransactionType.DEPOSIT);
        account.setMinBalance(newCustomer.getMinDeposit());

        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setTransactionAmount(newCustomer.getMinDeposit());
        account.addTransaction(transaction);
        customer.addAccount(account);

        customerRepository.save(customer);
        return customer;
    }

    @Override
    public Customer getCustomer(Long customerId) {
        return customerRepository.findByCustomerId(customerId);
    }
}
