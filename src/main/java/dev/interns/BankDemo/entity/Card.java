package dev.interns.BankDemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    private boolean isActive;

    private Long cardNumber;

    private Long bankAccNum;

    private Long customerId;

    private Long cvv;

    private String expiry;
    

    public Card(boolean isActive, Long cardNumber, Long bankAccNum, Long customerId, Long cvv, String expiry) {
        this.isActive = isActive;
        this.cardNumber = cardNumber;
        this.bankAccNum = bankAccNum;
        this.customerId = customerId;
        this.cvv = cvv;
        this.expiry = expiry;
    }
}

