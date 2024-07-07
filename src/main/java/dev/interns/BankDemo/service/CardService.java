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

    public List<Card> getCardsByCustomerId(Long customerId) {
        return cardRepository.findByCustomerId(customerId);
    }

    // card needs to be activated after it is created
    public Card createCard(Long bankAccNum, Long customerId) {
        Long cardNumber = generateCardNumber();
        Long cvv = generateCvv();
        String expiry = generateExpiry();

        Card card = new Card(false, cardNumber, bankAccNum, customerId, cvv, expiry );
        return cardRepository.save(card);
    }

    // return true if successfully activate, false if fail
    public boolean activateCard(Long cardId) {
        Optional<Card> cardOpt = cardRepository.findById(cardId);

        if(cardOpt.isPresent()) {
            cardOpt.get().setActive(true);
            cardRepository.save(cardOpt.get());
            return true;
        } else {
            return false;
        }
    }

    // return true if successfully block, false if fail
    public boolean blockCard(Long cardId) {
        Optional<Card> cardOpt = cardRepository.findById(cardId);

        if(cardOpt.isPresent()) {
            cardOpt.get().setActive(false);
            cardRepository.save(cardOpt.get());
            return true;
        } else {
            return false;
        }
    }

    // generate 16 random numbers for card numbers
    public static Long generateCardNumber() {
        Random random = new Random();

        long lowerBound = 1_000_000_000_000_000L;
        long upperBound = 10_000_000_000_000_000L;

        return lowerBound + (long) (random.nextDouble() * (upperBound - lowerBound));
    }

    // generate 3 random numbers for cvv
    public static Long generateCvv() {
        Random random = new Random();

        long lowerBound = 100L;
        long upperBound = 1000L;

        return lowerBound + (long) (random.nextDouble() * (upperBound - lowerBound));
    }

    // generate expiry date
    public static String generateExpiry() {
        Random random = new Random();
        int month = 1 + random.nextInt(12);
        int year = 2024 + random.nextInt(10);
        return month + "/" + year;
    }
    
}


