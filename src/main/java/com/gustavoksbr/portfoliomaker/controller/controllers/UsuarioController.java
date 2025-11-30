package com.gustavoksbr.portfoliomaker.controller.controllers;

import com.gustavoksbr.portfoliomaker.controller.dtos.usuario.CodigoRecuperacaoSenhaRequest;
import com.gustavoksbr.portfoliomaker.controller.dtos.usuario.EsqueciSenhaRequest;
import com.gustavoksbr.portfoliomaker.controller.dtos.usuario.UsuarioRequest;
import com.gustavoksbr.portfoliomaker.domain.models.JwtResponse;
import com.gustavoksbr.portfoliomaker.domain.UsuarioManager;
import com.gustavoksbr.portfoliomaker.domain.models.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioManager usuarioManager;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticate(@RequestBody @Valid UsuarioRequest usuario){
        JwtResponse response = usuarioManager.login(usuario.toDomain());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<JwtResponse> criarUsuario (@RequestBody @Valid UsuarioRequest usuario) {
        JwtResponse jwtResponse = new JwtResponse(usuarioManager.criarUsuario(usuario.toDomain()));
        return ResponseEntity.ok(jwtResponse);
    }
    @PostMapping("/usuario/esquecisenha")
    public ResponseEntity<Void> esqueciSenha(@RequestBody @Valid EsqueciSenhaRequest esqueciSenhaRequest){
        Usuario usuario = Usuario.builder().email(esqueciSenhaRequest.getEmail()).build();
        usuarioManager.esqueciSenha(usuario);
        return ResponseEntity.ok().build();

    }
    @PostMapping("/usuario/novasenha")
    public ResponseEntity<JwtResponse> novaSenha(@RequestBody @Valid CodigoRecuperacaoSenhaRequest codigoPorEmailRequest){
        Usuario usuario = Usuario.builder().email(codigoPorEmailRequest.getEmail()).senha(codigoPorEmailRequest.getNovaSenha()).build();
        System.out.println("usuario: "+usuario.toString());
        JwtResponse jwtResponse = new JwtResponse(usuarioManager.alterarSenha(usuario, codigoPorEmailRequest.getCodigo()));
        return ResponseEntity.ok(jwtResponse);
    }
}
