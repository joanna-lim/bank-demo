package dev.interns.BankDemo.controller;

import dev.interns.BankDemo.entity.Transaction;
import dev.interns.BankDemo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @PostMapping("/transfer")
    public ResponseEntity<Optional<Transaction>> transferMoney(
            @RequestParam Long fromBankAccNum,
            @RequestParam Long toBankAccNum,
            @RequestParam Double amount) {
        Optional<Transaction> transaction = transactionService.transferMoney(fromBankAccNum, toBankAccNum, amount);
        return transaction.isPresent()
                ? new ResponseEntity<>(transaction, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/account/{bankAccNum}")
    public ResponseEntity<List<Transaction>> getTransactionsByBankAccNum(@PathVariable Long bankAccNum) {
        List<Transaction> transactions = transactionService.getTransactionsByBankAccNum(bankAccNum);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
