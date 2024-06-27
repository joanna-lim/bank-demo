package dev.interns.BankDemo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.interns.BankDemo.entity.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    Optional<BankAccount> findByBankAccNum(Long bankAccNum);
    List<BankAccount> findByCustomerId(Long customerId);
}
