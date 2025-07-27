package com.example.query_service.query_service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/query")
public class BankAccountQueryController {

    private final BankAccountEventConsumer consumer;

    public BankAccountQueryController(BankAccountEventConsumer consumer) {
        this.consumer = consumer;
    }

    @GetMapping("/balance")
    public ResponseEntity<Double> getBalance(@RequestParam String accountId) {
        return ResponseEntity.ok(consumer.getBalance(accountId));
    }
}

