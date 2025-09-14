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
    public String ping() {
        var now = System.currentTimeMillis();
        System.out.println("Alguém pingou: "+now);
        return "teste - " + System.currentTimeMillis();
    }

    private final RestTemplate restTemplate = new RestTemplate();

    // http://localhost:8080/ping
    // https://portfoliomakerback.onrender.com/ping

    private static final String SELF_URL = "https://portfoliomakerback.onrender.com/ping";

    // A cada 10 minutos (10 * 60 * 1000 ms)
    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void scheduledHello() {
        try {
            String response = restTemplate.getForObject(SELF_URL, String.class);
            System.out.println("Auto-Ping OK: " + response);
        } catch (Exception e) {
            System.err.println("Erro ao pingar a si mesmo: " + e.getMessage());
        }
    }
}
