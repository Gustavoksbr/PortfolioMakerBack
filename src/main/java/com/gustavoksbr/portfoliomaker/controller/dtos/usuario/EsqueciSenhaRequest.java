package com.gustavoksbr.portfoliomaker.controller.dtos.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EsqueciSenhaRequest {
    @NotBlank
    @Email
    private String email;
}
