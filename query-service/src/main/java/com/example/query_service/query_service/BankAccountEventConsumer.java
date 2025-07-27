package com.example.query_service.query_service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class BankAccountEventConsumer {

    private final Map<String, Double> balances = new ConcurrentHashMap<>();

    @KafkaListener(topics = "bank-events", groupId = "bank-group")
    public void consume(String message) throws ParseException {
        System.out.println("🔥 GOT MESSAGE: " + message);
        JSONParser parser = new JSONParser();
        
        JSONObject jsonObject = (JSONObject) parser.parse(message);
        JSONObject event = new JSONObject(jsonObject);
        String type = (String) event.get("type");
        String accountId = (String) event.get("accountId");

        balances.putIfAbsent(accountId, 0.0);

        switch (type) {
            case "MoneyDeposited":
                balances.compute(accountId, (k, v) -> v + (Double) event.get("amount"));
                break;
            case "MoneyWithdrawn":
                balances.compute(accountId, (k, v) -> v - (Double) event.get("amount"));
                break;
            // AccountCreated does not affect balance
        }

        System.out.println("Updated balance for " + accountId + ": " + balances.get(accountId));
    }

    public Double getBalance(String accountId) {
        return balances.getOrDefault(accountId, 0.0);
    }
}
