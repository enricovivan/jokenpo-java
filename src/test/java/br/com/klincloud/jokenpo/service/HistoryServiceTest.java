package br.com.klincloud.jokenpo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.klincloud.jokenpo.model.GameModel;
import br.com.klincloud.jokenpo.repository.GamesRepository;

@ExtendWith(MockitoExtension.class)
public class HistoryServiceTest {

    @Mock
    private GamesRepository gamesRepository;

    @InjectMocks
    private HistoryService historyService;

    private GameModel gameModelMock;
    private UUID uuid;

    @BeforeEach
    void setUp() {
        uuid = UUID.randomUUID();
        gameModelMock = new GameModel();
        gameModelMock.setId(uuid);
    }

    @Test
    void testGetAllGames_ShouldReturnList() {
        when(gamesRepository.findAll()).thenReturn(List.of(gameModelMock));
        List<GameModel> result = historyService.getAllGames();
        assertEquals(1, result.size());
        assertEquals(gameModelMock, result.get(0));
    }

    @Test
    void testGetAllGames_ShouldReturnEmptyListWhenEmpty() {
        when(gamesRepository.findAll()).thenReturn(List.of());
        List<GameModel> result = historyService.getAllGames();
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetGameById_ShouldReturnGameWhenFound() {
        when(gamesRepository.findById(uuid)).thenReturn(Optional.of(gameModelMock));
        GameModel result = historyService.getGameById(uuid);
        assertEquals(gameModelMock, result);
    }

    @Test
    void testGetGameById_ShouldReturnNullWhenNotFound() {
        when(gamesRepository.findById(uuid)).thenReturn(Optional.empty());
        GameModel result = historyService.getGameById(uuid);
        assertNull(result);
    }

    @Test
    void testGetAllGamesFromPlayerName_ShouldReturnList() {
        when(gamesRepository.findByPlayerName("Test")).thenReturn(List.of(gameModelMock));
        List<GameModel> result = historyService.getAllGamesFromPlayerName("Test");
        assertEquals(1, result.size());
    }

    @Test
    void testGetAllGamesFromPlayerName_ShouldReturnEmptyList() {
        when(gamesRepository.findByPlayerName("Test")).thenReturn(List.of());
        List<GameModel> result = historyService.getAllGamesFromPlayerName("Test");
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetAllGamesFromPlayerId_ShouldReturnList() {
        when(gamesRepository.findByPlayerId(uuid)).thenReturn(List.of(gameModelMock));
        List<GameModel> result = historyService.getAllGamesFromPlayerId(uuid);
        assertEquals(1, result.size());
    }

    @Test
    void testGetAllGamesFromPlayerId_ShouldReturnEmptyList() {
        when(gamesRepository.findByPlayerId(uuid)).thenReturn(List.of());
        List<GameModel> result = historyService.getAllGamesFromPlayerId(uuid);
        assertTrue(result.isEmpty());
    }
}
