package com.switchlink.api.services;

import com.switchlink.api.dto.TransactionDTO;
import com.switchlink.api.models.Account;
import com.switchlink.api.models.Transaction;
import com.switchlink.api.models.TransactionType;
import com.switchlink.api.repositories.AccountRepository;
import com.switchlink.api.repositories.TransactionRepository;
import com.switchlink.api.response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    public TransactionRepository transactionRepository;

    @Autowired
    public AccountRepository accountRepository;

    @Override
    public ResponseEntity<Object> getTransactionsByAccount(Long accNo) {
        Account account = getActualBalance(accNo);

        if (account == null){
            return ResponseHandler.generateResponse("No account found for the account number",HttpStatus.NOT_FOUND, null);
        }
        else {
            List<TransactionDTO> transaction = transactionRepository.findTransactionByAccount_AccNo(accNo)
                    .stream().map(this::convertToTransactionDTO).collect(Collectors.toList());
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, transaction);
        }
    }

    @Override
    public ResponseEntity<Object> depositToOwnAccount(Transaction newTransaction) {
        Account accounts = getActualBalance(newTransaction.getSenderAccNo());

        //check if account exists
        if (accounts == null){
            return ResponseHandler.generateResponse("No account found for the account number",HttpStatus.NOT_FOUND, null);
        }
        else{
            //add new amount to minimum balance
            double minBalance = accounts.getMinBalance();
            double depositedAmount = newTransaction.getTransactionAmount();
            double newBalance = minBalance + depositedAmount;

            Account account = getAccount(newTransaction.getSenderAccNo());
            Transaction transaction1 = new Transaction();
            transaction1.setTransactionType(TransactionType.DEPOSIT);
            transaction1.setTransactionAmount(depositedAmount);

            return ResponseHandler.generateResponse("Success", HttpStatus.OK, setBalance(account, newBalance, transaction1));
        }
    }

    @Override
    public ResponseEntity<Object> accountToAccountTransfer(Transaction newTransaction) {
        Account senderAccount = getActualBalance(newTransaction.getSenderAccNo());
        Account receiverAccount = getActualBalance(newTransaction.getReceiverAccNo());

        //check if both sender and receiver accounts exist
        if ((senderAccount == null) || (receiverAccount == null)) {
            return ResponseHandler.generateResponse("No account found for the account number",HttpStatus.NOT_FOUND, null);
        }

        //check if sender account is 0 or less than amount to be withdrawn
        if ((senderAccount.getMinBalance() == 0) || (senderAccount.getMinBalance() < newTransaction.getTransactionAmount())) {
            return ResponseHandler.generateResponse("Insufficient funds ",HttpStatus.NOT_FOUND, null);

        } else {

            //subtract amount from sender
            double senderMinBalance = senderAccount.getMinBalance();
            double depositedAmount = newTransaction.getTransactionAmount();
            double newSenderBalance = senderMinBalance - depositedAmount;

            Account account = getAccount(newTransaction.getSenderAccNo());
            Transaction senderTransaction = new Transaction();
            senderTransaction.setTransactionType(TransactionType.TRANSFER_OUT);
            senderTransaction.setTransactionAmount(newTransaction.getTransactionAmount());
            senderTransaction.setReceiverAccNo(newTransaction.getReceiverAccNo());

            //update new balance
            Account senAccount = setBalance(account, newSenderBalance, senderTransaction);

            //add subtracted amount to receiver
            double receiverMinBalance = receiverAccount.getMinBalance();
            double newReceiverBalance = receiverMinBalance + depositedAmount;

            Account account1 = getAccount(newTransaction.getReceiverAccNo());
            Transaction receiverTransaction = new Transaction();
            receiverTransaction.setTransactionType(TransactionType.TRANSFER_IN);
            receiverTransaction.setTransactionAmount(depositedAmount);

            //update receiver balance
            setBalance(account1, newReceiverBalance, receiverTransaction);

            return ResponseHandler.generateResponse("Success",HttpStatus.OK, senAccount);
        }
    }

    @Override
    public ResponseEntity<Object> accountWithdrawal(Transaction newTransaction) {
        Account accounts = getActualBalance(newTransaction.getSenderAccNo());

        //check if account exists
        if (accounts == null) {
            return ResponseHandler.generateResponse("No account found for the account number",HttpStatus.NOT_FOUND, null);

        } else {

            double minBalance = accounts.getMinBalance();
            double withdrawalAmount = newTransaction.getTransactionAmount();
            double newBalance = minBalance - withdrawalAmount;

            Account account = getAccount(newTransaction.getSenderAccNo());
            Transaction transaction1 = new Transaction();
            transaction1.setTransactionType(TransactionType.WITHDRAWAL);
            transaction1.setTransactionAmount(withdrawalAmount);

            return ResponseHandler.generateResponse("Success",HttpStatus.OK, setBalance(account, newBalance, transaction1));
        }
    }

    private Account setBalance(Account account, double newBalance, Transaction transaction1) {
        account.setMinBalance(newBalance);
        Transaction transaction = new Transaction();
        transaction.setTransactionType(transaction1.getTransactionType());
        transaction.setTransactionAmount(transaction1.getTransactionAmount());
        account.addTransaction(transaction);
        return accountRepository.save(account);
    }
    private Account getActualBalance(Long accNo) {
        return accountRepository.findAccountByAccNo(accNo);
    }
    private Account getAccount(Long accNo) {
        return accountRepository.findAccountByAccNo(accNo);
    }

    private TransactionDTO convertToTransactionDTO(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionReferenceNo(transaction.getTransactionReferenceNo());
        transactionDTO.setTransactionType(transaction.getTransactionType());
        transactionDTO.setTransactionAmount(transaction.getTransactionAmount());
        return transactionDTO;
    }
}
