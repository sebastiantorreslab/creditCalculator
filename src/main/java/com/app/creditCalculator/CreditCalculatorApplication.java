package com.app.creditCalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class CreditCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditCalculatorApplication.class, args);
	}

}
