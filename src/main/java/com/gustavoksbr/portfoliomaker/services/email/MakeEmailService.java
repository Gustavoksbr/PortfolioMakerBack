package com.gustavoksbr.portfoliomaker.services.email;

import com.gustavoksbr.portfoliomaker.domain.dtos.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
@Profile("make")
public class MakeEmailService implements EmailService {

    private final String apiUrl;
    private final RestTemplate restTemplate;

    public MakeEmailService(@Value("${make.api}") String apiUrl) {
        this.apiUrl = apiUrl;
        this.restTemplate = new RestTemplate();
    }

    public void sendEmail(Email email) {
 System.out.println("Enviando email com a api da Make");
        CompletableFuture.runAsync(() -> {
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                Map<String, String> payload = new HashMap<>();
                payload.put("to", email.getTo());
                payload.put("subject", email.getSubject());
                payload.put("body", email.getBody());

                HttpEntity<Map<String, String>> request = new HttpEntity<>(payload, headers);

                restTemplate.postForEntity(apiUrl, request, String.class);
            } catch (Exception e) {
                throw new RuntimeException("Erro ao enviar e-mail via Make", e);
            }
        });
    }
}
