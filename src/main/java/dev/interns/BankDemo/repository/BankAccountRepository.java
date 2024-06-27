package dev.interns.BankDemo.repository;

import dev.interns.BankDemo.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    Optional<BankAccount> findByBankAccNum(Long bankAccNum);

    List<BankAccount> findByCustomerId(Long customerId);

    
}
