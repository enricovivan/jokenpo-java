package br.com.klincloud.jokenpo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.klincloud.jokenpo.model.PlayerModel;
import br.com.klincloud.jokenpo.repository.PlayerRepository;

@Service
public class PlayerService {
    
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<PlayerModel> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Optional<PlayerModel> getPlayerById(UUID id) {
        return playerRepository.findById(id);
    }

}
