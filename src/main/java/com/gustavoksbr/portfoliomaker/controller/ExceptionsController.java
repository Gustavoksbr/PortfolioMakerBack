package com.gustavoksbr.portfoliomaker.controller;

import com.gustavoksbr.portfoliomaker.domain.models.exceptions.*;
import com.mongodb.MongoException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.oauth2.jwt.BadJwtException;
import org.springframework.security.oauth2.jwt.JwtValidationException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ExceptionsController {
    public ExceptionsController() {
    }

    @ExceptionHandler(ErroDeRequisicaoGeral.class)
    public ResponseEntity<String> handleErroDeRequisicaoGeral(ErroDeRequisicaoGeral ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler({NoHandlerFoundException.class})
    public ResponseEntity<String> NoResourceFoundException(NoHandlerFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro 404: URL não encontrada.");
    }

    // provavelmente erro no corpo da requisição com spring validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder erros = new StringBuilder();

        // Extrai os erros de campo e os adiciona à string
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            System.out.println("errorMessage: ("+errorMessage+")");
            if(errorMessage!=null && errorMessage.equals("deve corresponder a \"^[a-zA-Z0-9]+$\"")) {
                errorMessage = "deve conter apenas letras e números.";
            }
            erros.append("Campo '").append(fieldName).append("': ").append(errorMessage).append(". ");
        });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(erros.toString().trim());
    }

    // erro explícito no corpo da requisição
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Corpo da requisição inválido: "+ex.getMessage());
    }

    // ====================================================================================================
    // 403
    // nao necessariamente sobre jwt ou login

    @ExceptionHandler(UsuarioNaoAutorizado.class)
    public ResponseEntity<String> handleUsuarioNaoAutorizado(UsuarioNaoAutorizado ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    @ExceptionHandler(Erro409.class)
    public ResponseEntity<String> handleErro409(Erro409 ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    //405
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Método HTTP não permitido para esta URL.");
    }
//
//    // ====================================================================================================
//    // 500
    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception ex) {
        System.out.println("Erro 500: "+ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor.");
    }

    @ExceptionHandler(MongoException.class)
    public ResponseEntity<String> handleMongoException(MongoException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro no acesso ao banco de dados.");
    }

    // usuario


    @ExceptionHandler(UsuarioNaoEncontrado.class) //404
    public ResponseEntity<String> handleUsuarioNaoEncontrado(UsuarioNaoEncontrado ex) {
        System.out.println("http!!!!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }


    //401

    @ExceptionHandler(SenhaIncorretaException.class)
    public ResponseEntity<String> handleSenhaIncorretaException(SenhaIncorretaException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta.");
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<String> handleExpiredJwtException(ExpiredJwtException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token expirado. Logue-se novamente.");
    }

    @ExceptionHandler(JwtValidationException.class)
    public ResponseEntity<String> handleJwtValidationException(JwtValidationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token expirado. Logue-se novamente.");
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<String> handleMalformedJwtException(MalformedJwtException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido. Logue-se novamente.");
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<String> handleSignatureException(SignatureException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token ausente ou assinatura inválida. Logue-se novamente.");
    }
    @ExceptionHandler(BadJwtException.class)
    public ResponseEntity<String> handleBadJwtException(BadJwtException ex) {
        if(ex.getMessage().startsWith("An error occurred while attempting to decode the Jwt: Jwt expired at")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token expirado. Logue-se novamente.");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token expirado. Logue-se novamente.");
    }
    @ExceptionHandler(ErroDeAutenticacaoGeral.class)
    public ResponseEntity<String> handleErroDeAutenticacaoGeral(ErroDeAutenticacaoGeral ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token expirado. Logue-se novamente.");
    }

    //409
    @ExceptionHandler(EmailJaExiste.class)
    public ResponseEntity<String> handleEmailJaExiste(EmailJaExiste ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}