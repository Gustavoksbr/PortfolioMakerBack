package com.gustavoksbr.portfoliomaker.domain.dtos.exceptions;

public class ErroDeAutenticacaoGeral extends RuntimeException {
    public ErroDeAutenticacaoGeral(String message) {
        super(message);
    }
}
