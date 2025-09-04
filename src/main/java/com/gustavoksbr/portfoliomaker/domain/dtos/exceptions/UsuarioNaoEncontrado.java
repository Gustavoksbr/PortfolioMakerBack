package com.gustavoksbr.portfoliomaker.domain.dtos.exceptions;

public class UsuarioNaoEncontrado  extends RuntimeException {
    public UsuarioNaoEncontrado(String username) {
        super("Usuário "+username+" não encontrado");
    }
}
