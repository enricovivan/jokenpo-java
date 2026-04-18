package br.com.klincloud.jokenpo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.klincloud.jokenpo.model.PlayerModel;
import br.com.klincloud.jokenpo.repository.PlayerRepository;
import br.com.klincloud.jokenpo.service.PlayerService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/players")
public class PlayerController {
    
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("all")
    public ResponseEntity<List<PlayerModel>> getAllPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PlayerModel>> getPlayerById(@PathVariable UUID id) {

        var player = playerService.getPlayerById(id);

        if (player.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(playerService.getPlayerById(id));
    }
    
    

}
