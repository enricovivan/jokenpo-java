package br.com.klincloud.jokenpo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.klincloud.jokenpo.dto.request.PlayRequestDto;
import br.com.klincloud.jokenpo.dto.response.PlayResponseDto;
import br.com.klincloud.jokenpo.enumerate.Jokenpo;
import br.com.klincloud.jokenpo.enumerate.Result;
import br.com.klincloud.jokenpo.model.GameModel;
import br.com.klincloud.jokenpo.model.PlayerModel;
import br.com.klincloud.jokenpo.repository.GamesRepository;
import br.com.klincloud.jokenpo.repository.PlayerRepository;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {
    
    @Mock
    private GamesRepository gamesRepository;

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private GameService gameService;

    private PlayerModel playerMock;
    private PlayRequestDto requestMock;

    @BeforeEach
    void setUp() {
        playerMock = new PlayerModel(UUID.randomUUID(), "TestPlayer");
        requestMock = new PlayRequestDto();
        requestMock.setPlayer(playerMock);
        requestMock.setOption(Jokenpo.PEDRA);
    }

    @Test
    void testPlay_WhenPlayerDoesNotExist_ShouldCreatePlayerAndPlay() {
        // Arrange
        when(playerRepository.findByName(playerMock.getName())).thenReturn(Optional.empty());
        when(playerRepository.save(playerMock)).thenReturn(playerMock);
        when(gamesRepository.save(any(GameModel.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        PlayResponseDto response = gameService.play(requestMock);

        // Assert
        verify(playerRepository).findByName(playerMock.getName());
        verify(playerRepository).save(playerMock);
        
        ArgumentCaptor<GameModel> gameCaptor = ArgumentCaptor.forClass(GameModel.class);
        verify(gamesRepository).save(gameCaptor.capture());
        
        GameModel savedGame = gameCaptor.getValue();
        assertEquals(playerMock, savedGame.getPlayer());
        assertEquals(requestMock.getOption(), savedGame.getPlayerOption());
        assertNotNull(savedGame.getComputerOption());
        assertEquals(response.getResult(), savedGame.getResult());
        
        assertGameLogic(requestMock.getOption(), response.getComputerOption(), response);
    }

    @Test
    void testPlay_WhenPlayerExists_ShouldNotCreatePlayerAndPlay() {
        // Arrange
        when(playerRepository.findByName(playerMock.getName())).thenReturn(Optional.of(playerMock));
        when(gamesRepository.save(any(GameModel.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        PlayResponseDto response = gameService.play(requestMock);

        // Assert
        verify(playerRepository).findByName(playerMock.getName());
        verify(playerRepository, never()).save(any(PlayerModel.class));
        
        verify(gamesRepository).save(any(GameModel.class));
        
        assertGameLogic(requestMock.getOption(), response.getComputerOption(), response);
    }

    @Test
    void testPrivateResultLogic() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = GameService.class.getDeclaredMethod("result", Jokenpo.class, Jokenpo.class);
        method.setAccessible(true);
        
        // Empates
        assertEquals(Result.DRAW, method.invoke(gameService, Jokenpo.PEDRA, Jokenpo.PEDRA));
        assertEquals(Result.DRAW, method.invoke(gameService, Jokenpo.PAPEL, Jokenpo.PAPEL));
        assertEquals(Result.DRAW, method.invoke(gameService, Jokenpo.TESOURA, Jokenpo.TESOURA));

        // Vitórias do jogador
        assertEquals(Result.WIN, method.invoke(gameService, Jokenpo.PAPEL, Jokenpo.PEDRA));
        assertEquals(Result.WIN, method.invoke(gameService, Jokenpo.TESOURA, Jokenpo.PAPEL));
        assertEquals(Result.WIN, method.invoke(gameService, Jokenpo.PEDRA, Jokenpo.TESOURA));

        // Derrotas do jogador
        assertEquals(Result.LOSE, method.invoke(gameService, Jokenpo.PEDRA, Jokenpo.PAPEL));
        assertEquals(Result.LOSE, method.invoke(gameService, Jokenpo.PAPEL, Jokenpo.TESOURA));
        assertEquals(Result.LOSE, method.invoke(gameService, Jokenpo.TESOURA, Jokenpo.PEDRA));
    }

    private void assertGameLogic(Jokenpo playerOption, Jokenpo computerOption, PlayResponseDto response) {
        if (playerOption == computerOption) {
            assertEquals(Result.DRAW, response.getResult());
            assertEquals("Empate!", response.getMessage());
        } else if (
            (playerOption == Jokenpo.PEDRA && computerOption == Jokenpo.TESOURA) ||
            (playerOption == Jokenpo.PAPEL && computerOption == Jokenpo.PEDRA) ||
            (playerOption == Jokenpo.TESOURA && computerOption == Jokenpo.PAPEL)
        ) {
            assertEquals(Result.WIN, response.getResult());
            assertEquals("Você venceu!", response.getMessage());
        } else {
            assertEquals(Result.LOSE, response.getResult());
            assertEquals("Você perdeu!", response.getMessage());
        }
    }
}
