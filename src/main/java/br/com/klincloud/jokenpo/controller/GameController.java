package br.com.klincloud.jokenpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.klincloud.jokenpo.dto.request.PlayRequestDto;
import br.com.klincloud.jokenpo.dto.response.PlayResponseDto;
import br.com.klincloud.jokenpo.service.GameService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    // rota para jogar
    @PostMapping("play")
    public ResponseEntity<PlayResponseDto> play(@RequestBody @Valid PlayRequestDto req){
        return ResponseEntity.ok(gameService.play(req));
    }


}