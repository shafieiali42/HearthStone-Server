package controller.response;

public class ChangeBackOfCardsResponse extends Response {



    private String mode;

    public ChangeBackOfCardsResponse(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
