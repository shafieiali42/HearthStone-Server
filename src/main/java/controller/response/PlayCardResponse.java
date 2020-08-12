package controller.response;

public class PlayCardResponse extends Response {


    private String message;
    private String playingCardName;


    public PlayCardResponse(String message,String playingCardName) {
        setResponseType("PlayCardResponse");
        this.message = message;
        this.playingCardName=playingCardName;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPlayingCardName() {
        return playingCardName;
    }

    public void setPlayingCardName(String playingCardName) {
        this.playingCardName = playingCardName;
    }
}
