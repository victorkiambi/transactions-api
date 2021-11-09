package com.switchlink.api.services;

import com.switchlink.api.dto.AccountDTO;
import com.switchlink.api.models.Account;
import com.switchlink.api.models.Customer;
import com.switchlink.api.repositories.AccountRepository;
import com.switchlink.api.repositories.CustomerRepository;
import com.switchlink.api.response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<Object> getAccount(Long customerId) {

        Customer customer = customerRepository.findByCustomerId(customerId);

        if(customer == null){
            return ResponseHandler.generateResponse("Customer not found", HttpStatus.NOT_FOUND,null);
        }else
        {
            List<AccountDTO> account = accountRepository.findByCustomer_CustomerId(customerId)
                    .stream()
                    .map(this::convertToTransactionDTO).collect(Collectors.toList());

            return ResponseHandler.generateResponse("Success", HttpStatus.OK, account);

        }

    }

    private AccountDTO  convertToTransactionDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccNo(account.getAccNo());
        accountDTO.setMinBalance(account.getMinBalance());
        accountDTO.setCustomer(account.getCustomer());
        return accountDTO;
    }
}
