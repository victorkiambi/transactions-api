package com.switchlink.api.services;

import com.switchlink.api.models.Transaction;
import org.springframework.http.ResponseEntity;

public interface TransactionService {
    ResponseEntity<Object> depositToOwnAccount(Transaction newTransaction);

    ResponseEntity<Object> getTransactionsByAccount(Long accNo);

    ResponseEntity<Object> accountToAccountTransfer(Transaction newTransaction);

    ResponseEntity<Object> accountWithdrawal(Transaction newTransaction);
}
