package com.gustavoksbr.portfoliomaker.services.repository.portfolio;

import com.gustavoksbr.portfoliomaker.domain.dtos.Portfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PortfolioRepositoy {
    private final PortfolioMongoRepository portfolioMongoRepository;
    @Autowired
    public PortfolioRepositoy(PortfolioMongoRepository portfolioMongoRepository) {
        this.portfolioMongoRepository = portfolioMongoRepository;
    }
    public Portfolio savePortfolio(Portfolio portfolio) {
        PortfolioEntity portfolioEntity = new PortfolioEntity(portfolio);
        return this.portfolioMongoRepository.save(portfolioEntity).toDomain();
    }
    public Portfolio findByEmail(String email) {
       return this.portfolioMongoRepository.findByEmail(email)
                .map(PortfolioEntity::toDomain)
                .orElse(null);
    }
    public Portfolio findByUsername(String username) {
        return this.portfolioMongoRepository.findByUsername(username)
                .map(PortfolioEntity::toDomain)
                .orElse(null);
    }
    public void deletePortfolioByUsername(String username) {
        this.portfolioMongoRepository.deleteByUsername(username);
    }

    public List<Portfolio> listar() {
        return this.portfolioMongoRepository.findAll()
                .stream()
                .map(PortfolioEntity::toDomain)
                .toList();
    }
}
