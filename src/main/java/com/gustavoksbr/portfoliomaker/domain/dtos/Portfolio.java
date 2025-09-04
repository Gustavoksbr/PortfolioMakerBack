package com.gustavoksbr.portfoliomaker.domain.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Portfolio {
    private String id;
    private String username; //identificadores unicos
    private String email; //identificadores unicos
    private String nome;
    private String breveDescricao;
    private String descricao;
    private Imagem foto;

    private Set<String> habilidades;
    private List<Projeto> projetos;
    private List<Experiencia> experiencias;

    private Imagem background;
    private String localizacao;
    private List<Link> links;


    public void atualizar(Portfolio portfolio) {
        this.username = portfolio.getUsername();
        this.nome = portfolio.getNome();
        this.breveDescricao = portfolio.getBreveDescricao();
        this.descricao = portfolio.getDescricao();
        this.foto = portfolio.getFoto();
        this.habilidades = portfolio.getHabilidades();
        this.projetos = portfolio.getProjetos();
        this.experiencias = portfolio.getExperiencias();
        this.background = portfolio.getBackground();
        this.localizacao = portfolio.getLocalizacao();
        this.links = portfolio.getLinks();
    }
}
