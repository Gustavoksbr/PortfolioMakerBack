package com.gustavoksbr.portfoliomaker.controller.dtos.portfolio;

import com.gustavoksbr.portfoliomaker.domain.models.*;
import com.gustavoksbr.portfoliomaker.domain.models.exceptions.ErroDeRequisicaoGeral;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PortfolioRequest {
    @Pattern(regexp = "^[a-zA-Z0-9 _-]+$")
    @Size(max = 20)
    @NotBlank
    private String username;

    @Size(max = 30)
    @NotBlank
    private String nome;

    @Size(max = 80)
    private String breveDescricao;

    @Size(max = 400)
    private String descricao;
    private Imagem foto;
    private Set<@Size(max = 15)String> habilidades;
    private List<@Valid ProjetoRequest> projetos;
    private List<ExperienciaRequest> experiencias;
    private Imagem background;
    @Size(max=50)
    private String localizacao;
    private List<@Valid LinkRequest> links;

    public Portfolio toDomain() {
        Portfolio portfolio = new Portfolio();
        List<Projeto> projetos = new ArrayList<>();
        List<Experiencia> experiencias = new ArrayList<>();
        List<Link> links = new ArrayList<>();
        portfolio.setFoto(this.foto);

        if (this.projetos != null && !this.projetos.isEmpty()) {
            Set<Integer> ordens = new HashSet<>();

            for (ProjetoRequest projetoRequest : this.projetos) {
                Integer ordem = projetoRequest.getOrdem();
                if (!ordens.add(ordem)) {
                    throw new ErroDeRequisicaoGeral("Nos projetos, ordem duplicada detectada: " + ordem);
                }
                String url = projetoRequest.getLinkDoProjeto();
                if (url != null && !url.isBlank() && !url.startsWith("http://") && !url.startsWith("https://")) {
                    projetoRequest.setLinkDoProjeto("https://" + url);
                }
                projetos.add(projetoRequest.toDomain());
            }
        }
        if(this.experiencias != null && !this.experiencias.isEmpty()) {
            Set<Integer> ordens = new HashSet<>();

            for (ExperienciaRequest experienciaRequest : this.experiencias) {
                Integer ordem = experienciaRequest.getOrdem();
                if (!ordens.add(ordem)) {
                    throw new ErroDeRequisicaoGeral("Nas experiências, ordem duplicada detectada: " + ordem);
                }
                experiencias.add(experienciaRequest.toDomain());
            }
        }
        if (this.links != null && !this.links.isEmpty()) {
            Set<String> nomes = new HashSet<>();

            for (LinkRequest linkRequest : this.links) {
                String nome = linkRequest.getNome();
                String url = linkRequest.getUrl();
                if(nome != null && !nome.isBlank() && url != null && !url.isBlank()) {
                    if (!nomes.add(nome)) {
                        throw new ErroDeRequisicaoGeral("Nos links, nome duplicado detectado: " + nome);
                    }
                    if (!url.startsWith("http://") && !url.startsWith("https://")) {
                        linkRequest.setUrl("https://" + url);
                    }
                    links.add(linkRequest.toDomain());
                }
            }
        }
        portfolio.setUsername(this.username);
        portfolio.setNome(this.nome);
        portfolio.setBreveDescricao(this.breveDescricao);
        portfolio.setDescricao(this.descricao);
        portfolio.setHabilidades(this.habilidades);
        portfolio.setProjetos(projetos);
        portfolio.setExperiencias(experiencias);
        portfolio.setBackground(this.background);
        portfolio.setLocalizacao(this.localizacao);
        portfolio.setLinks(links);

        return portfolio;
    }
}
