package br.com.klincloud.jokenpo.dto.request;

import br.com.klincloud.jokenpo.enumerate.Jokenpo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PlayRequestDto {
    
    @NotNull
    private Jokenpo option;

    @NotBlank(message = "Player name is invalid")
    @NotNull
    private String playerName;

    public Jokenpo getOption() {
        return option;
    }

    public void setOption(Jokenpo option) {
        this.option = option;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    

}
