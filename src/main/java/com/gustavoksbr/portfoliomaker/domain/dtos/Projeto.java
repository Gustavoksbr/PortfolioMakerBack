package com.gustavoksbr.portfoliomaker.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Projeto {
    private Integer ordem;
    private String nome;
    private String descricao;
    private String linkDoProjeto;
    private String linkDoRepositorio;
    private String linkYoutube;
    private Imagem imagem;
    private Set<String> tecnologias;
}
