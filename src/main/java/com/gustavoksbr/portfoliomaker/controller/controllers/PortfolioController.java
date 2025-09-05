package com.gustavoksbr.portfoliomaker.controller.controllers;

import com.gustavoksbr.portfoliomaker.controller.dtos.portfolio.PortfolioRequest;
import com.gustavoksbr.portfoliomaker.domain.PortfolioManager;
import com.gustavoksbr.portfoliomaker.domain.UsuarioManager;
import com.gustavoksbr.portfoliomaker.domain.dtos.Portfolio;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/portfolios")
public class PortfolioController {
    private final PortfolioManager portfolioManager;
    private final UsuarioManager usuarioManager;
    @Autowired
    public PortfolioController(PortfolioManager portfolioManager, UsuarioManager usuarioManager) {
        this.usuarioManager = usuarioManager;
        this.portfolioManager = portfolioManager;
    }
    @PostMapping("/save")
    public ResponseEntity<Portfolio> savePortfolio(@Valid @RequestBody PortfolioRequest portfolioRequest, HttpServletRequest httpServletRequest) {
        String email = httpServletRequest.getAttribute("email").toString();
        System.out.println("email: " + email);
        Portfolio portfolio = portfolioRequest.toDomain();
        portfolio.setEmail(email);
        this.portfolioManager.savePortfolio(portfolio);
        return ResponseEntity.ok(portfolio);
    }
    @GetMapping("/username/{portfolioUsername}")
    public ResponseEntity<Portfolio> getPortfolioByUsername(@PathVariable String portfolioUsername) {

        Portfolio portfolio = this.portfolioManager.buscarPorUsername(portfolioUsername);
        if (portfolio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(portfolio);
    }

    @GetMapping("/email/{portfolioEmail}")
    public ResponseEntity<Portfolio> getPortfolioByEmail(@PathVariable String portfolioEmail) {
        if(usuarioManager.existePorEmail(portfolioEmail)) {
            Portfolio portfolio = this.portfolioManager.buscarPorEmail(portfolioEmail);
            if (portfolio == null) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(portfolio);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping()
    public ResponseEntity<List<Portfolio>> getPortfolios() {
        List<Portfolio> portfolios = this.portfolioManager.listar();
        return ResponseEntity.ok(portfolios);
    }

@GetMapping("/no-gustavoksbr")
public ResponseEntity<List<Portfolio>> getPortfoliosNoGustavoksbr() {
    List<Portfolio> portfolios = this.portfolioManager.listar();
    List<Portfolio> portfolios1 = new ArrayList<>();
    for (Portfolio portfolio : portfolios) {
        if (!portfolio.getEmail().equals("gustavosalesi@hotmail.com")) {
            portfolios1.add(portfolio);
        }
    }
    return ResponseEntity.ok(portfolios1);
}

//    @DeleteMapping("/username/{portfolioUsername}")
//    public ResponseEntity<Void> deletePortfolioByUsername(@PathVariable String portfolioUsername, HttpServletRequest httpServletRequest) {
//        Portfolio portfolio = this.portfolioManager.buscarPorUsername(portfolioUsername);
//        if (portfolio == null) {
//            return ResponseEntity.notFound().build();
//        }
//        this.portfolioManager.deletarPortfolio(portfolio.getUsername(), httpServletRequest.getAttribute("email").toString());
//        return ResponseEntity.ok().build();
//    }
}
