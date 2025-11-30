package com.gustavoksbr.portfoliomaker.domain.models.exceptions;

public class SalaJaExiste extends RuntimeException {
    public SalaJaExiste(String nome) {
        super("Você já possui uma sala com o nome: '" + nome + ".");
    }
}