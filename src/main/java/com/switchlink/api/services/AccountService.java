package com.switchlink.api.services;


import org.springframework.http.ResponseEntity;

public interface AccountService {
    ResponseEntity<Object> getAccount(Long customerId);
}
