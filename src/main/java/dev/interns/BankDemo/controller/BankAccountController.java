package dev.interns.BankDemo.controller;

import dev.interns.BankDemo.entity.BankAccount;
import dev.interns.BankDemo.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/bankaccounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping
    public ResponseEntity<BankAccount> createBankAccount(@RequestBody BankAccount bankAccount) {
        BankAccount createdAccount = bankAccountService.createBankAccount(
                bankAccount.getBankAccNum(),
                bankAccount.getBalance(),
                bankAccount.getCustomerId()
        );
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BankAccount>> getAllBankAccounts() {
        List<BankAccount> bankAccounts = bankAccountService.getAllBankAccounts();
        return new ResponseEntity<>(bankAccounts, HttpStatus.OK);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<BankAccount>> getCustomerBankAccounts(@PathVariable Long customerId) {
        List<BankAccount> bankAccounts = bankAccountService.getBankAccountsByCustomerId(customerId);
        return new ResponseEntity<>(bankAccounts, HttpStatus.OK);
    }

    @PutMapping("/addBalance")
    public ResponseEntity<Optional<BankAccount>> addBalance(@RequestParam Long bankAccNum, @RequestParam Double amount) {
        Optional<BankAccount> bankAccount = bankAccountService.addBalance(bankAccNum, amount);
        return bankAccount.isPresent()
                ? new ResponseEntity<>(bankAccount, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/removeBalance")
    public ResponseEntity<Optional<BankAccount>> removeBalance(@RequestParam Long bankAccNum, @RequestParam Double amount) {
        try {
            Optional<BankAccount> bankAccount = bankAccountService.removeBalance(bankAccNum, amount);
            return bankAccount.isPresent()
                    ? new ResponseEntity<>(bankAccount, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
