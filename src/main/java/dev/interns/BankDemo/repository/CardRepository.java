package dev.interns.BankDemo.repository;

import dev.interns.BankDemo.entity.BankAccount;
import dev.interns.BankDemo.entity.Card;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByCustomerId(Long customerId);
}


