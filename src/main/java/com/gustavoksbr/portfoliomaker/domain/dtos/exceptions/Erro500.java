package com.gustavoksbr.portfoliomaker.domain.dtos.exceptions;

public class Erro500 extends RuntimeException {
    public Erro500(String message) {
        super(message);
    }
}
