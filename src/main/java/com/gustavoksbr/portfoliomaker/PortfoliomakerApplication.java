package com.gustavoksbr.portfoliomaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PortfoliomakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfoliomakerApplication.class, args);
	}

}
