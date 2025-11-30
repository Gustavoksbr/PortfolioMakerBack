package com.gustavoksbr.portfoliomaker.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Experiencia {
    private Integer ordem;
    private String nome;
    private String empresa;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Boolean atual;
}
