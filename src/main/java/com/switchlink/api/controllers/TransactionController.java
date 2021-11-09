package com.switchlink.api.controllers;

import com.switchlink.api.models.Transaction;
import com.switchlink.api.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {

    public TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @GetMapping("/api/v1/transactions/{accNo}")
    public ResponseEntity<Object> getTransactions(@PathVariable Long accNo){
        return transactionService.getTransactionsByAccount(accNo);
    }

    @PostMapping("/api/v1/transaction/deposit")
    public ResponseEntity<Object> depositToOwnAccount(@RequestBody Transaction newTransaction)  {
        return transactionService.depositToOwnAccount(newTransaction);
    }

    @PostMapping("/api/v1/transaction/transfer")
    public ResponseEntity<Object> accountToAccountTransfer(@RequestBody Transaction newTransaction) {
        return transactionService.accountToAccountTransfer(newTransaction);
    }

    @PostMapping("/api/v1/transaction/withdraw")
    public ResponseEntity<Object> withdrawFromAccount(@RequestBody Transaction newTransaction) {
        return transactionService.accountWithdrawal(newTransaction);
    }
}
