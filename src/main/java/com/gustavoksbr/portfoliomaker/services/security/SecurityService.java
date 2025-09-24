package com.gustavoksbr.portfoliomaker.services.security;

import com.gustavoksbr.portfoliomaker.domain.dtos.Email;
import com.gustavoksbr.portfoliomaker.domain.dtos.Usuario;
import com.gustavoksbr.portfoliomaker.domain.dtos.exceptions.ErroDeRequisicaoGeral;
import com.gustavoksbr.portfoliomaker.domain.dtos.exceptions.SenhaIncorretaException;
import com.gustavoksbr.portfoliomaker.services.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;

@Service
public class SecurityService{
    private final JwtService jwtService;

    private final EmailService emailService;

    private final PasswordEncoder passwordEncoder;

    private final HashMap<String, String> verificationCodes = new HashMap<>();

    private final HashMap<String, String> esqueciSenhaCodes = new HashMap<>();

    @Autowired
    public SecurityService(JwtService jwtService, EmailService emailService, PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }
    // metodos PasswordEncoder
    public void matches(CharSequence rawPassword, String encodedPassword) {
        System.out.println("rawPassword: " + rawPassword);
        System.out.println("encodedPassword: " + encodedPassword);
        if (!passwordEncoder.matches(rawPassword, encodedPassword)){
            throw new SenhaIncorretaException();
        }
    }

    public String encode(String password) {
        return passwordEncoder.encode(password);
    }

    // metodos JwtService
    public String generateToken(String subject) {
        return jwtService.generateToken(subject);
    }

    public String getEmail(String token) {

        return jwtService.getEmail(token);
    }

    // metodos EmailService
    public void sendVerificationCode(String email) {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        String verificationCode = String.valueOf(code);
        this.verificationCodes.put(email, verificationCode);
        this.emailService.sendEmail(Email.builder()
                .to(email)
                .subject("Código de autenticação")
                .body("Código de autenticacao: " + verificationCode)
                .build());
    }

    public String validarCodigo(String email, String code) {
        String verificationCode = this.verificationCodes.get(email);
        if(verificationCode == null) {
            throw new ErroDeRequisicaoGeral("Código de autenticação não encontrado (ou expirado). Solicite um novo código.");
        }
        if(verificationCode.equals(code)) {
            this.verificationCodes.remove(email);
            return this.generateToken(email);
        } else {
            throw new ErroDeRequisicaoGeral("Código incorreto");
        }
    }

    public void sendEsqueciSenhaCode(String email) {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        String esqueciSenhaCode = String.valueOf(code);
        this.esqueciSenhaCodes.put(email, esqueciSenhaCode);
        this.emailService.sendEmail(Email.builder()
                .to(email)
                .subject("Recuperação de senha")
                .body("Código de recuperação de senha de PortfolioMaker: " + esqueciSenhaCode)
                .build());
    }

    public String alterarSenha(Usuario usuarioEncontrado, String codigo) {
        String esqueciSenhaCode = this.esqueciSenhaCodes.get(usuarioEncontrado.getEmail());
        if(esqueciSenhaCode == null) {
            throw new ErroDeRequisicaoGeral("Código de recuperação de senha não encontrado (ou expirado). Solicite um novo código.");
        }
        if(esqueciSenhaCode.equals(codigo)) {
            this.esqueciSenhaCodes.remove(usuarioEncontrado.getEmail());
            return this.generateToken(usuarioEncontrado.getEmail());
        } else {
            throw new ErroDeRequisicaoGeral("Código incorreto");
        }
    }
}
