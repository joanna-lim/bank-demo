package dev.interns.BankDemo.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dev.interns.BankDemo.entity.BankAccount;
import dev.interns.BankDemo.repository.BankAccountRepository;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.restTemplate = new RestTemplate();
    }

    public Optional<BankAccount> getAccountDetails(Long accountId) {
        return bankAccountRepository.findByBankAccNum(accountId);
    }

    public List<BankAccount> getCustomerAccounts(Long customerId) {
        return bankAccountRepository.findByCustomerId(customerId);
    }

    public BankAccount createBankAccount(Double balance, Long customerId) {
        Long bankAccNum = generateBankAccountNumber();
        BankAccount bankAccount = new BankAccount(bankAccNum, balance, customerId);
        return bankAccountRepository.save(bankAccount);
    }

    public static Long generateBankAccountNumber() {
        Random random = new Random();
        long bankAccNum = 1000000 + random.nextInt(9000000); 
        return bankAccNum; 
    }

    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    public List<BankAccount> getBankAccountsByCustomerId(Long customerId) {
        return bankAccountRepository.findByCustomerId(customerId);
    }

    public Optional<BankAccount> addBalance(Long bankAccNum, Double amount) {
        Optional<BankAccount> bankAccountOpt = bankAccountRepository.findByBankAccNum(bankAccNum);
        if (bankAccountOpt.isPresent()) {
            BankAccount bankAccount = bankAccountOpt.get();
            bankAccount.setBalance(bankAccount.getBalance() + amount);
            bankAccountRepository.save(bankAccount);
            return Optional.of(bankAccount);
        }
        return Optional.empty();
    }

    public Optional<BankAccount> removeBalance(Long bankAccNum, Double amount) {
        Optional<BankAccount> bankAccountOpt = bankAccountRepository.findByBankAccNum(bankAccNum);
        if (bankAccountOpt.isPresent()) {
            BankAccount bankAccount = bankAccountOpt.get();
            if (bankAccount.getBalance() >= amount) {
                bankAccount.setBalance(bankAccount.getBalance() - amount);
                bankAccountRepository.save(bankAccount);
                return Optional.of(bankAccount);
            } else {
                throw new IllegalArgumentException("Insufficient balance");
            }
        }
        return Optional.empty();
    }

    // Example method to interact with Supabase directly
    public BankAccount saveToSupabase(BankAccount account) {
        // Implement HTTP POST request to Supabase
        // Example:
        // HttpHeaders headers = new HttpHeaders();
        // headers.set("apiKey", API_KEY);
        // HttpEntity<BankAccount> request = new HttpEntity<>(account, headers);
        // ResponseEntity<BankAccount> response = restTemplate.postForEntity(SUPABASE_URL + BANK_ACCOUNTS_ENDPOINT, request, BankAccount.class);
        // return response.getBody();
        return account;
    }
}
