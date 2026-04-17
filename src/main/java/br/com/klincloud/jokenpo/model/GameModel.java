package br.com.klincloud.jokenpo.model;

import java.util.UUID;

import br.com.klincloud.jokenpo.enumerate.Result;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "games")
public class GameModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String playerName;
    private String computerOption;
    private String playerOption;
    private Result result;

    public GameModel() {
    }

    public GameModel (
        String playerName,
        String computerOption,
        String playerOption,
        Result result
    ) {
        this.playerName = playerName;
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
    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public String getComputerOption() {
        return computerOption;
    }
    public void setComputerOption(String computerOption) {
        this.computerOption = computerOption;
    }
    public String getPlayerOption() {
        return playerOption;
    }
    public void setPlayerOption(String playerOption) {
        this.playerOption = playerOption;
    }
    public Result getResult() {
        return result;
    }
    public void setResult(Result result) {
        this.result = result;
    }

    

}
