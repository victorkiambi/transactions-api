package com.switchlink.api.repositories;

import com.switchlink.api.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByCustomer_CustomerId(Long customerId);
}
