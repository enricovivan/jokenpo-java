package br.com.klincloud.jokenpo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.klincloud.jokenpo.model.GameModel;
import br.com.klincloud.jokenpo.repository.GamesRepository;

@Service
public class HistoryService {
    
    private final GamesRepository gameRepository;

    public HistoryService(GamesRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<GameModel> getAllGames() {
        var games = gameRepository.findAll();

        if (games.stream().toList().size() == 0) {
            return List.of();
        }

        return games.stream().toList();
    }

    public GameModel getGameById(UUID id) {
        var game = gameRepository.findById(id);

        if (game.isPresent()) {
            return game.get();
        }

        return null;
    }

    public List<GameModel> getAllGamesFromPlayerName(String playerName){
        var games = gameRepository.findByPlayerName(playerName);

        if (games.isEmpty()) {
            return List.of();
        }

        return games.stream().toList();
    }

    public List<GameModel> getAllGamesFromPlayerId(UUID playerId){
        var games = gameRepository.findByPlayerId(playerId);

        if (games.isEmpty()) {
            return List.of();
        }

        return games.stream().toList();
    }

}
