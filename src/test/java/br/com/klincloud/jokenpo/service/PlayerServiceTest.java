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

import br.com.klincloud.jokenpo.model.PlayerModel;
import br.com.klincloud.jokenpo.repository.PlayerRepository;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerService;

    private PlayerModel playerMock;
    private UUID uuid;

    @BeforeEach
    void setUp() {
        uuid = UUID.randomUUID();
        playerMock = new PlayerModel(uuid, "TestPlayer");
    }

    @Test
    void testGetAllPlayers_ShouldReturnList() {
        when(playerRepository.findAll()).thenReturn(List.of(playerMock));
        List<PlayerModel> result = playerService.getAllPlayers();
        assertEquals(1, result.size());
        assertEquals(playerMock, result.get(0));
    }

    @Test
    void testGetPlayerById_ShouldReturnOptionalWithPlayer() {
        when(playerRepository.findById(uuid)).thenReturn(Optional.of(playerMock));
        Optional<PlayerModel> result = playerService.getPlayerById(uuid);
        assertTrue(result.isPresent());
        assertEquals(playerMock, result.get());
    }

    @Test
    void testGetPlayerById_ShouldReturnEmptyOptionalWhenNotFound() {
        when(playerRepository.findById(uuid)).thenReturn(Optional.empty());
        Optional<PlayerModel> result = playerService.getPlayerById(uuid);
        assertFalse(result.isPresent());
    }
}
