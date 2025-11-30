package com.gustavoksbr.portfoliomaker.domain;

import com.gustavoksbr.portfoliomaker.domain.models.Portfolio;
import com.gustavoksbr.portfoliomaker.domain.models.exceptions.Erro404;
import com.gustavoksbr.portfoliomaker.domain.models.exceptions.Erro409;
import com.gustavoksbr.portfoliomaker.services.repository.portfolio.PortfolioRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioManager {
    private final PortfolioRepositoy portfolioRepositoy;

    @Autowired
    public PortfolioManager(PortfolioRepositoy portfolioRepositoy) {
        this.portfolioRepositoy = portfolioRepositoy;
    }
    public Portfolio savePortfolio(Portfolio portfolio) {

        Portfolio portfolioEncontradoPorUsername = portfolioRepositoy.findByUsername(portfolio.getUsername());
        if (portfolioEncontradoPorUsername != null) {
            if(portfolioEncontradoPorUsername.getEmail().equals(portfolio.getEmail())) {
                portfolioEncontradoPorUsername.atualizar(portfolio);
                return portfolioRepositoy.savePortfolio(portfolioEncontradoPorUsername);
            }
                throw new Erro409("Um portfolio com esse username já é de outro usuário");
        }
        Portfolio portfolioEncontradoPorEmail = portfolioRepositoy.findByEmail(portfolio.getEmail());
        if (portfolioEncontradoPorEmail != null) {
            if(!portfolioEncontradoPorEmail.getUsername().equals(portfolio.getUsername())) {
                this.portfolioRepositoy.deletePortfolioByUsername(portfolioEncontradoPorEmail.getUsername());
            }
        }
        return portfolioRepositoy.savePortfolio(portfolio);
    }

    public List<Portfolio> listar(){
        return this.portfolioRepositoy.listar();
    }

    public Portfolio buscarPorUsername(String username){
        return this.portfolioRepositoy.findByUsername(username);
    }
    public Portfolio buscarPorEmail(String email){
        return this.portfolioRepositoy.findByEmail(email);
    }

    public void deletarPortfolio(String username, String email){
        Portfolio portfolio = this.portfolioRepositoy.findByUsername(username);
        if(portfolio == null){
            throw new Erro404("Portfolio não encontrado");
        }
        if(!portfolio.getEmail().equals(email)){
            throw new Erro409("Seu email não é o mesmo do portfolio");
        }
        this.portfolioRepositoy.deletePortfolioByUsername(username);
    }
}
