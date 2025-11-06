package com.gustavoksbr.portfoliomaker.controller.dtos.usuario;

import com.gustavoksbr.portfoliomaker.domain.dtos.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRequest {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 3, max = 64)
    private String senha;

    public Usuario toDomain() {
        return new Usuario(email, senha);
    }
}
