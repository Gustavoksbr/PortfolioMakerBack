package com.gustavoksbr.portfoliomaker.services.email;

import com.gustavoksbr.portfoliomaker.domain.models.Email;

public interface EmailService {
    void sendEmail(Email email);
}
