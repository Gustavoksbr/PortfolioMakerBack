package com.gustavoksbr.portfoliomaker.controller.dtos.portfolio;

import com.gustavoksbr.portfoliomaker.domain.models.Imagem;
import com.gustavoksbr.portfoliomaker.domain.models.Projeto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjetoRequest {
    @NotNull
    private Integer ordem;

    @NotBlank
    @Size(min = 1, max = 40)
    private String nome;

    @Size(max = 400)
    private String descricao;

    private String linkDoProjeto;
    private String linkDoRepositorio;
    private String linkYoutube;
    private Imagem imagem;
    private Set<@Size(max = 15)String> tecnologias;

    public Projeto toDomain() {
        Projeto projeto = new Projeto();
        projeto.setImagem(imagem);
        projeto.setOrdem(this.ordem);
        projeto.setNome(this.nome);
        projeto.setDescricao(this.descricao);
        projeto.setLinkDoProjeto(this.linkDoProjeto);
        projeto.setLinkDoRepositorio(this.linkDoRepositorio);
        projeto.setLinkYoutube(this.linkYoutube);
        projeto.setTecnologias(this.tecnologias);
        return projeto;
    }
}
