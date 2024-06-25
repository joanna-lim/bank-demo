package dev.interns.BankDemo.service;

import dev.interns.BankDemo.entity.BankAccount;
import dev.interns.BankDemo.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public BankAccount createBankAccount(Long bankAccNum, Double balance, Long customerId) {
        BankAccount bankAccount = new BankAccount(bankAccNum, balance, customerId);
        return bankAccountRepository.save(bankAccount);
    }

    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
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
}
