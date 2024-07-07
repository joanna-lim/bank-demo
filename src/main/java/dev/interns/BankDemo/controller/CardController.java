package dev.interns.BankDemo.controller;

import dev.interns.BankDemo.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev.interns.BankDemo.entity.Card;
import dev.interns.BankDemo.service.CardService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/{customerId}")
    public ResponseEntity<List<Card>> getCardsByCustomerId(@PathVariable Long customerId) {
       // sort the cards by card number before returning
        List<Card> cards = cardService.getCardsByCustomerId(customerId);
        cards.sort((c1, c2) -> c1.getCardNumber().compareTo(c2.getCardNumber()));
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Card> createCard(@RequestParam Long bankAccNum, @RequestParam Long customerId) {
        Card card = cardService.createCard(bankAccNum, customerId);
        return new ResponseEntity<>(card, HttpStatus.CREATED);
    }

    @PutMapping("/activate/{cardId}")
    public ResponseEntity<String> activateCard(@PathVariable Long cardId) {
        boolean success = cardService.activateCard(cardId);
        if (success) {
            return new ResponseEntity<>("Card activated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Card not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/block/{cardId}")
    public ResponseEntity<String> blockCard(@PathVariable Long cardId) {
        boolean success = cardService.blockCard(cardId);
        if (success) {
            return new ResponseEntity<>("Card blocked successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Card not found", HttpStatus.NOT_FOUND);
        }
    }
}
