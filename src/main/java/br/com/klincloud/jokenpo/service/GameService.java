package br.com.klincloud.jokenpo.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import br.com.klincloud.jokenpo.dto.request.PlayRequestDto;
import br.com.klincloud.jokenpo.dto.response.PlayResponseDto;
import br.com.klincloud.jokenpo.enumerate.Jokenpo;
import br.com.klincloud.jokenpo.enumerate.Result;
import br.com.klincloud.jokenpo.model.GameModel;
import br.com.klincloud.jokenpo.model.PlayerModel;
import br.com.klincloud.jokenpo.repository.GamesRepository;
import br.com.klincloud.jokenpo.repository.PlayerRepository;

@Service
public class GameService {

    private final GamesRepository gameRepository;
    private final PlayerRepository playerRepository;

    public GameService(GamesRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    // logica de negocio
    public PlayResponseDto play(PlayRequestDto req) {
        
        // verifica se o player existe no banco de dados, se nao existir cria e retorna
        PlayerModel player = verifyPlayer(req.getPlayer());
        Jokenpo playerOption = req.getOption();
        
        // gera uma jogada aleatória
        Jokenpo computer = Jokenpo.values()[new Random().nextInt(Jokenpo.values().length)];

        PlayResponseDto result = new PlayResponseDto();

        result.setPlayerOption(playerOption);
        result.setComputerOption(computer);

        var winner = result(playerOption, computer);
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
        gameRepository.save(
            new GameModel(player, computer, playerOption, winner)
        );

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

    // metodo para verificar se o player já existe no banco de dados, se não existir, cria
    private PlayerModel verifyPlayer(PlayerModel player) {
        String name = player.getName();

        var playerRepo = playerRepository.findByName(name);

        if (playerRepo.isEmpty()) {
            return playerRepository.save(player);
        }

        return playerRepo.get();
    }

}
