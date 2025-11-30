package com.gustavoksbr.portfoliomaker.services.email;

import com.gustavoksbr.portfoliomaker.domain.models.Email;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Profile("javamail")
public class JavaMailEmailService implements EmailService {
    private final JavaMailSender javaMailSender = new JavaMailSenderImpl();

    @Override
    public void sendEmail(Email email) {
        System.out.println("Enviando email com JavaMail");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getTo());
        message.setSubject(email.getSubject());
        message.setText(email.getBody());

        CompletableFuture.runAsync(() -> this.javaMailSender.send(message));
    }
}