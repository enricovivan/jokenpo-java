package br.com.klincloud.jokenpo.model;

import java.util.UUID;

import br.com.klincloud.jokenpo.enumerate.Jokenpo;
import br.com.klincloud.jokenpo.enumerate.Result;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "games")
public class GameModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private PlayerModel player;
    private Jokenpo computerOption;
    private Jokenpo playerOption;
    private Result result;

    public GameModel() {
    }

    public GameModel (
        PlayerModel player,
        Jokenpo computerOption,
        Jokenpo playerOption,
        Result result
    ) {
        this.player = player;
        this.computerOption = computerOption;
        this.playerOption = playerOption;
        this.result = result;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public PlayerModel getPlayer() {
        return player;
    }
    public void setPlayer(PlayerModel player) {
        this.player = player;
    }
    public Jokenpo getComputerOption() {
        return computerOption;
    }
    public void setComputerOption(Jokenpo computerOption) {
        this.computerOption = computerOption;
    }
    public Jokenpo getPlayerOption() {
        return playerOption;
    }
    public void setPlayerOption(Jokenpo playerOption) {
        this.playerOption = playerOption;
    }
    public Result getResult() {
        return result;
    }
    public void setResult(Result result) {
        this.result = result;
    }

    

}
