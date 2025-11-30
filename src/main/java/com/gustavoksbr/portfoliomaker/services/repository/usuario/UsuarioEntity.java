package com.gustavoksbr.portfoliomaker.services.repository.usuario;

import com.gustavoksbr.portfoliomaker.domain.models.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioEntity {
    @Id
    private String id;
    @Field("email")
    private String email;
    @Field("senha")
    private String senha;

    public UsuarioEntity (Usuario usuario){
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
    }

    public Usuario toUsuario() {
        return new Usuario(email,senha);
    }
}
