package com.gustavoksbr.portfoliomaker.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Habilidade {
    private int ordem;
    private String habilidade;
}
