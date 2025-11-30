package com.gustavoksbr.portfoliomaker.controller.dtos.portfolio;

import com.gustavoksbr.portfoliomaker.domain.models.Link;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class LinkRequest {
    @Size(max = 15)
    private String nome;
    private String url;

    public Link toDomain() {
        return Link.builder()
                .nome(this.nome)
                .url(this.url)
                .build();
    }
}
