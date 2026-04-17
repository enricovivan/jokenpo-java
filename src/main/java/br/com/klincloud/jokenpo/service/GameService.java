package br.com.klincloud.jokenpo.service;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.klincloud.jokenpo.dto.request.PlayRequestDto;
import br.com.klincloud.jokenpo.dto.response.PlayResponseDto;
import br.com.klincloud.jokenpo.enumerate.Jokenpo;
import br.com.klincloud.jokenpo.enumerate.Result;
import br.com.klincloud.jokenpo.model.GameModel;
import br.com.klincloud.jokenpo.repository.GamesRepository;

@Service
public class GameService {

    private final GamesRepository gameRepository;

    public GameService(GamesRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    // logica de negocio
    public PlayResponseDto play(PlayRequestDto req) {
        
        // gera uma jogada aleatória
        Jokenpo computer = Jokenpo.values()[new Random().nextInt(Jokenpo.values().length)];
        Jokenpo player = req.getOption();

        PlayResponseDto result = new PlayResponseDto();

        result.setPlayerOption(player.toString());
        result.setComputerOption(computer.toString());

        var winner = result(player, computer);
        if (winner == Result.WIN) {
            result.setResult(winner);
            result.setMessage("Você venceu!");
        } else if (winner == Result.LOSE) {
            result.setResult(winner);
            result.setMessage("Você perdeu!");
        } else {
            result.setResult(winner);
            result.setMessage("Empate!");
        }

        // salva o resultado no db
        gameRepository.save(new GameModel(req.getPlayerName(), computer.toString(), player.toString(), winner));

        return result;

    }

    private Result result(Jokenpo player, Jokenpo computer) {
        if (player == computer) {
            return Result.DRAW;
        }

        if (computer == Jokenpo.PEDRA) {
            return player == Jokenpo.PAPEL ? Result.WIN : Result.LOSE;
        }

        if (computer == Jokenpo.PAPEL) {
            return player == Jokenpo.TESOURA ? Result.WIN : Result.LOSE;
        }

        if (computer == Jokenpo.TESOURA) {
            return player == Jokenpo.PEDRA ? Result.WIN : Result.LOSE;
        }

        return null;
    }

}
