package com.switchlink.api.services;

import com.switchlink.api.dto.CustomerDTO;
import com.switchlink.api.models.Account;
import com.switchlink.api.models.Customer;
import com.switchlink.api.models.Transaction;
import com.switchlink.api.models.TransactionType;
import com.switchlink.api.repositories.AccountRepository;
import com.switchlink.api.repositories.CustomerRepository;
import com.switchlink.api.response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    public CustomerRepository customerRepository;

    @Autowired
    public AccountRepository accountRepository;

    @Autowired
    PasswordEncoder encoder;

    public CustomerServiceImpl(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public ResponseEntity<Object> save(Customer newCustomer) {
        Customer customer = new Customer();
        customer.setUsername(newCustomer.getUsername());
        customer.setEmail(newCustomer.getEmail());
        customer.setPhone(newCustomer.getPhone());
        customer.setPassword(encoder.encode(newCustomer.getPassword()));

        Account account = new Account();
        account.setTransactionType(TransactionType.DEPOSIT);
        account.setMinBalance(newCustomer.getMinDeposit());

        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setTransactionAmount(newCustomer.getMinDeposit());
        account.addTransaction(transaction);
        customer.addAccount(account);
        Customer customer1 =  customerRepository.save(customer);

        return ResponseHandler.generateResponse("Success", HttpStatus.OK, getCustomerDTO(customer1));

    }

    @Override
    public ResponseEntity<Object> getCustomer(Long customerId) {
        Customer customer = customerRepository.findByCustomerId(customerId);
        if (customer == null){
            return ResponseHandler.generateResponse("Error no customer found", HttpStatus.NOT_FOUND, null);
        }
        else{

            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerId(customer.getCustomerId());
            customerDTO.setUsername(customer.getUsername());
            customerDTO.setEmail(customer.getEmail());
            customerDTO.setPhone(customer.getPhone());
            customerDTO.setAccounts(customer.getAccounts());

            return ResponseHandler.generateResponse("Success", HttpStatus.OK, customerDTO);
        }

    }

    private CustomerDTO getCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setUsername(customer.getUsername());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhone(customer.getPhone());
        customerDTO.setAccounts(customer.getAccounts());
        return customerDTO;
    }
}