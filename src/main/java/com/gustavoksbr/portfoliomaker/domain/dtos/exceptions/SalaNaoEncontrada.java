package com.gustavoksbr.portfoliomaker.domain.dtos.exceptions;

public class SalaNaoEncontrada extends RuntimeException {
    public SalaNaoEncontrada(String nome, String dono) {
        super("Sala com nome '" + nome + "' do usuario '"+dono+"'  não encontrada.");
    }
}
