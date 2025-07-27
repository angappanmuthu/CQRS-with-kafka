package com.example.cqrs.cqrs;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class BankAccountCommandController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public BankAccountCommandController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAccount(@RequestParam String accountId, @RequestParam String owner) {
        String event = "{\"type\":\"AccountCreated\",\"accountId\":\"" + accountId + "\",\"owner\":\"" + owner + "\"}";
        kafkaTemplate.send("bank-events", accountId, event);
        return ResponseEntity.ok("Account creation command sent");
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestParam String accountId, @RequestParam double amount) {
        String event = "{\"type\":\"MoneyDeposited\",\"accountId\":\"" + accountId + "\",\"amount\":" + amount + "}";
        kafkaTemplate.send("bank-events", accountId, event);
        return ResponseEntity.ok("Deposit command sent");
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestParam String accountId, @RequestParam double amount) {
        String event = "{\"type\":\"MoneyWithdrawn\",\"accountId\":\"" + accountId + "\",\"amount\":" + amount + "}";
        kafkaTemplate.send("bank-events", accountId, event);
        return ResponseEntity.ok("Withdraw command sent");
    }
}
