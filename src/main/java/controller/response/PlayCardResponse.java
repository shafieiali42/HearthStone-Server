package controller.response;

public class PlayCardResponse extends Response {


    private String message;

    public PlayCardResponse(String message) {
        setResponseType("PlayCardResponse");
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
