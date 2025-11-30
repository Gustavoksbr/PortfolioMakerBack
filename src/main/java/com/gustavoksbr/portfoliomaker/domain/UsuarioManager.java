package com.gustavoksbr.portfoliomaker.domain;

import com.gustavoksbr.portfoliomaker.domain.models.JwtResponse;
import com.gustavoksbr.portfoliomaker.domain.models.Usuario;
import com.gustavoksbr.portfoliomaker.services.repository.usuario.UsuarioRepository;
import com.gustavoksbr.portfoliomaker.services.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioManager {
    private final SecurityService authService;
    private final UsuarioRepository usuarioRepository;
    @Autowired
    public UsuarioManager(UsuarioRepository usuarioRepository, SecurityService authService) {
        this.usuarioRepository = usuarioRepository;
        this.authService = authService;
    }

    public String criarUsuario(Usuario usuario) {
        String senhaCriptografada = authService.encode(usuario.getSenha());
        Usuario usuarioParaSalvar = new Usuario(usuario.getEmail(), senhaCriptografada);
        this.usuarioRepository.criar(usuarioParaSalvar);
        return authService.generateToken(usuario.getEmail());
    }
    public JwtResponse login(Usuario usuario) {
        Usuario usuarioEncontrado = this.usuarioRepository.encontrarPorEmail(usuario.getEmail());
        authService.matches(usuario.getSenha(), usuarioEncontrado.getSenha());
        return new JwtResponse(authService.generateToken(usuarioEncontrado.getEmail()));
    }
    public void esqueciSenha(Usuario usuario) {
        Usuario usuarioEncontrado = this.usuarioRepository.encontrarPorEmail(usuario.getEmail());
        this.authService.sendEsqueciSenhaCode(usuarioEncontrado.getEmail());
    }
    public String alterarSenha(Usuario usuario, String codigo) {
        Usuario usuarioEncontrado = this.usuarioRepository.encontrarPorEmail(usuario.getEmail());
        String jwt = this.authService.alterarSenha(usuarioEncontrado, codigo);
        usuarioEncontrado.setSenha(this.authService.encode(usuario.getSenha()));
        this.usuarioRepository.alterarSenha(usuarioEncontrado);
        return jwt;
    }

    public String capturarEmailDoToken(String token) {
        return this.authService.getEmail(token);
    }

    public Boolean existePorEmail(String email) {
        return this.usuarioRepository.existePorEmail(email);
    }
}
