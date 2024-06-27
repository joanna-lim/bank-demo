package dev.interns.BankDemo.repository;

import dev.interns.BankDemo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.fromBankAccNum = :bankAccNum OR t.toBankAccNum = :bankAccNum")
    List<Transaction> findByFromBankAccNumOrToBankAccNum(@Param("bankAccNum") Long bankAccNum);
}