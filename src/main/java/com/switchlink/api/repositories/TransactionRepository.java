package com.switchlink.api.repositories;

import com.switchlink.api.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findTransactionByAccount_AccNo(Long accNo);
}
