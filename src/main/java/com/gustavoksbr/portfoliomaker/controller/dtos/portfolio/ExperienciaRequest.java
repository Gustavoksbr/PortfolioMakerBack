package com.gustavoksbr.portfoliomaker.controller.dtos.portfolio;

import com.gustavoksbr.portfoliomaker.domain.models.Experiencia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExperienciaRequest {
    @NotBlank
    private Integer ordem;
    @Size(max = 40)
    private String nome;
    @Size(max = 40)
    private String empresa;
    @Size(max = 300)
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Boolean atual;

    public Experiencia toDomain(){
        Experiencia experiencia = new Experiencia();
        experiencia.setOrdem(this.ordem);
        experiencia.setNome(this.nome);
        experiencia.setEmpresa(this.empresa);
        experiencia.setDescricao(this.descricao);
        experiencia.setDataInicio(this.dataInicio);
        experiencia.setDataFim(this.dataFim);
        experiencia.setAtual(this.atual);
        return experiencia;
    }
}