package dev.interns.BankDemo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev.interns.BankDemo.entity.Card;
import dev.interns.BankDemo.service.CardService;

@RestController
@RequestMapping("/api/v1/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping("/create")
    public ResponseEntity<Card> createCard(@RequestParam Long bankAccNum, @RequestParam Long customerId) {
        Card card = cardService.createCard(bankAccNum, customerId);
        return new ResponseEntity<>(card, HttpStatus.CREATED);
    }

    @PutMapping("/activate/{cardId}")
    public ResponseEntity<Card> activateCard(@PathVariable Long cardId) {
        Optional<Card> cardOpt = cardService.activateCard(cardId);
        return cardOpt.map(card -> new ResponseEntity<>(card, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/block/{cardId}")
    public ResponseEntity<Card> blockCard(@PathVariable Long cardId) {
        Optional<Card> cardOpt = cardService.blockCard(cardId);
        return cardOpt.map(card -> new ResponseEntity<>(card, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
