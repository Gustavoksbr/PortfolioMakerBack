package com.gustavoksbr.portfoliomaker.controller.interceptos;

import com.gustavoksbr.portfoliomaker.domain.UsuarioManager;
import com.gustavoksbr.portfoliomaker.domain.dtos.exceptions.ErroDeAutenticacaoGeral;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;
@Component
public class JwtInterceptor implements HandlerInterceptor  {

    private final UsuarioManager usuarioManager;

    @Autowired
    public JwtInterceptor(UsuarioManager usuarioManager) {
        this.usuarioManager = usuarioManager;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String autorization = Optional.ofNullable(request.getHeader("Authorization")).orElseThrow(() -> new ErroDeAutenticacaoGeral("Usuário não autenticado"));
        String email = usuarioManager.capturarEmailDoToken(autorization);
        request.setAttribute("email", email);
        return true;
    }
}