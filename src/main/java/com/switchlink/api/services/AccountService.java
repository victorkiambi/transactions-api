package com.switchlink.api.services;


import com.switchlink.api.models.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAccount(Long customerId);
}
