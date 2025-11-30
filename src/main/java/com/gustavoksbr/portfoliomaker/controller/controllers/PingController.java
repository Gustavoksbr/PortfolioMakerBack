package com.gustavoksbr.portfoliomaker.controller.controllers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/ping")
public class PingController {
    @GetMapping
    public void ping() {
    }
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String SELF_URL = "https://portfoliomakerback.onrender.com/ping";
    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void scheduledParaDeixarARenderAtivadaSempre() {
            restTemplate.getForObject(SELF_URL, String.class);
    }
}



// http://localhost:8080/ping
// https://portfoliomakerback.onrender.com/ping