package com.switchlink.api.controllers;

import com.switchlink.api.models.Account;
import com.switchlink.api.repositories.AccountRepository;
import com.switchlink.api.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {
    @Autowired
    public AccountRepository accountRepository;

    public AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/api/v1/accounts/{customerId}")
    public List<Account> getAccount(@PathVariable Long customerId){
        return accountService.getAccount(customerId );
//        AccountDTO accountDTO = accountService.getAccount(accNo);
//        if (accountDTO == null){
//            return ResponseHandler.generateResponse("No account found for the account number", HttpStatus.NOT_FOUND,null);
//        }
//        return ResponseHandler.generateResponse("Success", HttpStatus.OK,accountDTO);
    }

}
