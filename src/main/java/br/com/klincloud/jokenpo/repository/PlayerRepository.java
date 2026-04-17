package br.com.klincloud.jokenpo.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.klincloud.jokenpo.model.PlayerModel;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerModel, UUID> {
    
    public Optional<PlayerModel> findByName(String name);

}
