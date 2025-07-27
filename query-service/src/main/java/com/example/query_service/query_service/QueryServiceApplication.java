package com.example.query_service.query_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;


@SpringBootApplication
@EnableKafka
public class QueryServiceApplication {
	public static void main(String[] args) {
		

		SpringApplication.run(QueryServiceApplication.class, args);
	}

}
