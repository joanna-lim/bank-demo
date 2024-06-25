package dev.interns.BankDemo.repository;

import dev.interns.BankDemo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}