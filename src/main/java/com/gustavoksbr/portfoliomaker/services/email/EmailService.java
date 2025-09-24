package com.gustavoksbr.portfoliomaker.services.email;

import com.gustavoksbr.portfoliomaker.domain.dtos.Email;

public interface EmailService {
    void sendEmail(Email email);
}
