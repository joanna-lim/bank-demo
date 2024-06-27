package dev.interns.BankDemo.repository;

import dev.interns.BankDemo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByFromBankAccNum(Long fromBankAccNum);
}