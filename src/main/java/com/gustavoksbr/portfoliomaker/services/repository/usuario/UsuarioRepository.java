package com.gustavoksbr.portfoliomaker.services.repository.usuario;

import com.gustavoksbr.portfoliomaker.domain.dtos.Usuario;
import com.gustavoksbr.portfoliomaker.domain.dtos.exceptions.EmailJaExiste;
import com.gustavoksbr.portfoliomaker.domain.dtos.exceptions.UsuarioNaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository {
    private final MongoUsuarioRepository mongoUsuarioRepository;
    @Autowired
    public UsuarioRepository(MongoUsuarioRepository mongoUsuarioRepository) {
        this.mongoUsuarioRepository = mongoUsuarioRepository;
    }
    private UsuarioEntity entityFindByEmail(String email) {
        return mongoUsuarioRepository.findByEmail(email).orElseThrow(()-> new UsuarioNaoEncontrado(email));
    }
    public void validarNovoUsuario(Usuario usuario) {
        String email = usuario.getEmail();
        if (mongoUsuarioRepository.existsByEmail(email)) {
            throw new EmailJaExiste(email);
        }
    }
    public void criar(Usuario usuario) {
        this.validarNovoUsuario(usuario);
        this.mongoUsuarioRepository.save(new UsuarioEntity(usuario));
    }
    public Usuario encontrarPorEmail(String email) {
        return this.entityFindByEmail(email).toUsuario();
    }
    public void alterarSenha(Usuario usuario) {
        UsuarioEntity usuarioEntity = this.entityFindByEmail(usuario.getEmail());
        usuarioEntity.setSenha(usuario.getSenha());
        this.mongoUsuarioRepository.save(usuarioEntity);
    }

}
