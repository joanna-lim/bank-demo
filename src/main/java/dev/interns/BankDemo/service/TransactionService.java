package dev.interns.BankDemo.service;

import dev.interns.BankDemo.entity.BankAccount;
import dev.interns.BankDemo.entity.Transaction;
import dev.interns.BankDemo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final BankAccountService bankAccountService;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, BankAccountService bankAccountService) {
        this.transactionRepository = transactionRepository;
        this.bankAccountService = bankAccountService;
    }

    // Get all transactions from the database by calling the findAll method on transactionRepository
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    //TODO get transactions filtered by customerID (all accts linked to them)

    // Performs a transfer between 2 accounts
    public Optional<Transaction> transferMoney(Long fromBankAccNum, Long toBankAccNum, Double amount) {
        try {
            // Calls bankAccountService.removeBalance to deduct the specified amount from the sender's account, on success, return an Optional with sender's BankAccount
            Optional<BankAccount> fromAccountOpt = bankAccountService.removeBalance(fromBankAccNum, amount);

            if (fromAccountOpt.isPresent()) {
                Optional<BankAccount> toAccountOpt = bankAccountService.addBalance(toBankAccNum, amount);

                if (toAccountOpt.isPresent()) {

                    Transaction transaction = new Transaction();
                    transaction.setFromBankAccNum(fromBankAccNum);
                    transaction.setToBankAccNum(toBankAccNum);
                    transaction.setAmount(amount);
                    transaction.setDatetime(LocalDateTime.now());
                    transactionRepository.save(transaction);

                    return Optional.of(transaction);
                }
            }
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    public List<Transaction> getTransactionsByBankAccNum(Long bankAccNum) {
        return transactionRepository.findByFromBankAccNumOrToBankAccNum(bankAccNum);
    }
}
