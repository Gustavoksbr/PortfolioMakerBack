package com.gustavoksbr.portfoliomaker.services.repository.portfolio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;


interface PortfolioMongoRepository extends MongoRepository<PortfolioEntity, String> {

    Optional<PortfolioEntity> findByUsername(String name);

    Optional<PortfolioEntity> findByEmail(String email);

    Boolean existsByEmail(String email);

    @Query("{ 'username': { $regex: ?0, $options: 'i' } }")
    List<PortfolioEntity> findByUsernameContaining(String substring);

    void deleteByUsername(String name);
}
