package com.gustavoksbr.portfoliomaker.domain.dtos.exceptions;

public class SenhaIncorretaException extends RuntimeException {
    public SenhaIncorretaException() {
        super("Senha incorreta");
    }
}
