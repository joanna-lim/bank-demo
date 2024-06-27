package dev.interns.BankDemo.repository;

import dev.interns.BankDemo.entity.Card;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}


