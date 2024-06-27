package dev.interns.BankDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.interns.BankDemo.entity.Card;
import dev.interns.BankDemo.repository.CardRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CardService {
    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    // card needs to be activated after it is created
    public Card createCard(Long bankAccNum, Long customerId) {
        Long cardNumber = generateCardNumber();

        Card card = new Card(false, cardNumber, bankAccNum, customerId);
        return cardRepository.save(card);
    }

    public Optional<Card> activateCard(Long cardId) {
        Optional<Card> cardOpt = cardRepository.findById(cardId);
        cardOpt.ifPresent(card -> {
            card.setActive(true);
            cardRepository.save(card);
        });
        return cardOpt;
    }

    public Optional<Card> blockCard(Long cardId) {
        Optional<Card> cardOpt = cardRepository.findById(cardId);
        cardOpt.ifPresent(card -> {
            card.setActive(false);
            cardRepository.save(card);
        });
        return cardOpt;
    }

    // generate 16 random numbers for card numbers
    public static Long generateCardNumber() {
        Random random = new Random();

        long lowerBound = 1_000_000_000_000_000L;
        long upperBound = 10_000_000_000_000_000L;

        return lowerBound + (long) (random.nextDouble() * (upperBound - lowerBound));
    }
    
}


