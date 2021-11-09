package com.switchlink.api.controllers;

import com.switchlink.api.repositories.AccountRepository;
import com.switchlink.api.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    public AccountRepository accountRepository;

    public AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/api/v1/accounts/{customerId}")
    public ResponseEntity<Object> getAccount(@PathVariable Long customerId){
        return accountService.getAccount(customerId );
    }

}
