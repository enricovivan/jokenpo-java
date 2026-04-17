package br.com.klincloud.jokenpo.dto.response;

import br.com.klincloud.jokenpo.enumerate.Result;

public class PlayResponseDto {
    
    private String message;
    
    private Result result;

    private String playerOption;
    private String computerOption;

    public PlayResponseDto() {

    }
    
    public PlayResponseDto(String message, Result result, String playerOption, String computerOption) {
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

    public String getPlayerOption() {
        return playerOption;
    }

    public void setPlayerOption(String playerOption) {
        this.playerOption = playerOption;
    }

    public String getComputerOption() {
        return computerOption;
    }

    public void setComputerOption(String computerOption) {
        this.computerOption = computerOption;
    }
    
    

}
