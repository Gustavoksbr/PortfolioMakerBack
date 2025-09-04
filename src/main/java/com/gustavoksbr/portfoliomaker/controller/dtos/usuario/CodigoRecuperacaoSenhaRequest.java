package com.gustavoksbr.portfoliomaker.controller.dtos.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodigoRecuperacaoSenhaRequest {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String codigo;
    @NotBlank
    @Size(min = 3)
    private String novaSenha;

}