package com.gustavoksbr.portfoliomaker.services.repository.portfolio;

import com.gustavoksbr.portfoliomaker.domain.dtos.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Document(collection = "portfolios")

public class PortfolioEntity {
    @Id
    private String id;
    @Field("username")
    @Indexed(unique = true)
    private String username;
    @Field("email")
    @Indexed(unique = true)
    private String email;
    @Field("nome")
    private String nome;
    @Field("breveDescricao")
    private String breveDescricao;
    @Field("descricao")
    private String descricao;
    @Field("foto")
    private Imagem foto;
    @Field("habilidades")
    private Set<String> habilidades;
    @Field("projetos")
    private List<Projeto> projetos;
    @Field("experiencias")
    private List<Experiencia> experiencias;
    @Field("background")
    private Imagem background;
    @Field("localizacao")
    private String localizacao;
    @Field("links")
    private List<Link> links;

    public PortfolioEntity(Portfolio portfolio) {
        this.id = portfolio.getId();
        this.username = portfolio.getUsername();
        this.email = portfolio.getEmail();
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

    public Portfolio toDomain() {
        return Portfolio.builder()
                .id(id)
                .username(username)
                .email(email)
                .nome(nome)
                .breveDescricao(breveDescricao)
                .descricao(descricao)
                .foto(foto)
                .habilidades(habilidades)
                .projetos(projetos)
                .experiencias(experiencias)
                .background(background)
                .localizacao(localizacao)
                .links(links)
                .build();
    }
}
