package br.com.klincloud.jokenpo.dto.response;

import br.com.klincloud.jokenpo.enumerate.Jokenpo;
import br.com.klincloud.jokenpo.enumerate.Result;

public class PlayResponseDto {
    
    private String message;
    
    private Result result;

    private Jokenpo playerOption;
    private Jokenpo computerOption;

    public PlayResponseDto() {

    }
    
    public PlayResponseDto(String message, Result result, Jokenpo playerOption, Jokenpo computerOption) {
        this.message = message;
        this.result = result;
        this.playerOption = playerOption;
        this.computerOption = computerOption;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Jokenpo getPlayerOption() {
        return playerOption;
    }

    public void setPlayerOption(Jokenpo playerOption) {
        this.playerOption = playerOption;
    }

    public Jokenpo getComputerOption() {
        return computerOption;
    }

    public void setComputerOption(Jokenpo computerOption) {
        this.computerOption = computerOption;
    }
    
    

}
