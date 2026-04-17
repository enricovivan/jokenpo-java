package br.com.klincloud.jokenpo.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.klincloud.jokenpo.model.GameModel;

public interface GamesRepository extends JpaRepository<GameModel, UUID> {
    
    List<GameModel> findByPlayerName(String playerName);
    List<GameModel> findByPlayerId(UUID playerId);

}
