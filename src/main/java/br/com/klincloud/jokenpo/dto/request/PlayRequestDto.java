package br.com.klincloud.jokenpo.dto.request;

import br.com.klincloud.jokenpo.enumerate.Jokenpo;
import br.com.klincloud.jokenpo.model.PlayerModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PlayRequestDto {
    
    @NotNull
    private Jokenpo option;

    @NotNull
    private PlayerModel player;

    public Jokenpo getOption() {
        return option;
    }

    public void setOption(Jokenpo option) {
        this.option = option;
    }

    public PlayerModel getPlayer() {
        return player;
    }

    public void setPlayer(PlayerModel player) {
        this.player = player;
    }

}
