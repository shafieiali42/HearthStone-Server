package controller.response;

public class DiscoverPageResponse extends Response {


    private String cardName;

    public DiscoverPageResponse(String cardName) {
        this.cardName = cardName;
    }


    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

}
