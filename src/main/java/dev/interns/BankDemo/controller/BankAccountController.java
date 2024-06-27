package dev.interns.BankDemo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.interns.BankDemo.entity.BankAccount;
import dev.interns.BankDemo.service.BankAccountService;

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

    @GetMapping("/{accountId}")
    public ResponseEntity<BankAccount> getAccountDetails(@PathVariable Long accountId) {
        Optional<BankAccount> account = bankAccountService.getAccountDetails(accountId);
        return account.map(ResponseEntity::ok)
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<BankAccount>> getCustomerAccounts(@PathVariable Long customerId) {
        List<BankAccount> accounts = bankAccountService.getCustomerAccounts(customerId);
        return ResponseEntity.ok(accounts);
    }

    @GetMapping
    public ResponseEntity<List<BankAccount>> getAllBankAccounts() {
        List<BankAccount> bankAccounts = bankAccountService.getAllBankAccounts();
        return ResponseEntity.ok(bankAccounts);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<BankAccount>> getCustomerBankAccounts(@PathVariable Long customerId) {
        List<BankAccount> bankAccounts = bankAccountService.getBankAccountsByCustomerId(customerId);
        return new ResponseEntity<>(bankAccounts, HttpStatus.OK);
    }

    @PutMapping("/addBalance")
    public ResponseEntity<BankAccount> addBalance(@RequestParam Long bankAccNum, @RequestParam Double amount) {
        Optional<BankAccount> bankAccount = bankAccountService.addBalance(bankAccNum, amount);
        return bankAccount.map(ResponseEntity::ok)
                          .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/removeBalance")
    public ResponseEntity<BankAccount> removeBalance(@RequestParam Long bankAccNum, @RequestParam Double amount) {
        try {
            Optional<BankAccount> bankAccount = bankAccountService.removeBalance(bankAccNum, amount);
            return bankAccount.map(ResponseEntity::ok)
                              .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}



