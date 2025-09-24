package com.gustavoksbr.portfoliomaker.services.email;

import com.gustavoksbr.portfoliomaker.domain.dtos.Email;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.CompletableFuture;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final String from;

    public EmailService(JavaMailSender javaMailSender, @Value("${spring.mail.username}") String from) {
        this.javaMailSender = javaMailSender;
        this.from = from;
    }

    public void sendEmail(Email email) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setFrom(from, "PortfolioMaker");
            helper.setTo(email.getTo());
            helper.setSubject(email.getSubject());
            helper.setText(email.getBody(), false);

            CompletableFuture.runAsync(() -> javaMailSender.send(mimeMessage));
        } catch (MessagingException e) {
            throw new RuntimeException("Erro ao enviar e-mail", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Erro no nome do remetente", e);
        }
    }
}

