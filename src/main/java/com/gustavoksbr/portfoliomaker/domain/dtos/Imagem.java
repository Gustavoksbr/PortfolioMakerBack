package com.gustavoksbr.portfoliomaker.domain.dtos;

import lombok.Data;

@Data
public class Imagem {
    private String id;
    private String name;
    private String contentType;
    private byte[] data;
}
