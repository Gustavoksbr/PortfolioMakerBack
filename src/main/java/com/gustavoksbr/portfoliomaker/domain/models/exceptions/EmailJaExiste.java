package com.gustavoksbr.portfoliomaker.domain.models.exceptions;

public class EmailJaExiste extends RuntimeException {
    public EmailJaExiste(String email) {
        super("Usuário com email " + email + " já está cadastrado no sistema.");
    }
}