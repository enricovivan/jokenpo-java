package br.com.klincloud.jokenpo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.klincloud.jokenpo.model.GameModel;
import br.com.klincloud.jokenpo.service.HistoryService;

@RestController
@RequestMapping("/history")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<GameModel>> getAllGameHistory(){
        return ResponseEntity.ok(historyService.getAllGames());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameModel> getGameById(@PathVariable UUID id){
        return ResponseEntity.ok(historyService.getGameById(id));
    }

    @GetMapping("/player/{playerName}")
    public ResponseEntity<List<GameModel>> getAllGamesFromPlayerName(@PathVariable String playerName){
        return ResponseEntity.ok(historyService.getAllGamesFromPlayerName(playerName));
    }

}
