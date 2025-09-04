package com.gustavoksbr.portfoliomaker.domain.dtos.exceptions;

public class UsuarioNaoAutorizado extends RuntimeException {
    public UsuarioNaoAutorizado(String message) {
        super("Usuário não autorizado:"+message);
    }
}
