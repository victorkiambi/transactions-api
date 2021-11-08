package com.switchlink.api.services;

import com.switchlink.api.models.Account;
import com.switchlink.api.models.Transaction;

import java.util.List;

public interface TransactionService {
    Account depositToOwnAccount(Transaction newTransaction);

    List<Transaction> getTransactionsByAccount(Long accNo);

    Account accountToAccountTransfer(Transaction newTransaction);

    Account accountWithdrawal(Transaction newTransaction);
}
