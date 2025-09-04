package com.gustavoksbr.portfoliomaker.services.repository.usuario;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
interface MongoUsuarioRepository extends MongoRepository<UsuarioEntity, String> {
    Optional<UsuarioEntity> findByEmail(String email);
    Boolean existsByEmail(String email);
}
