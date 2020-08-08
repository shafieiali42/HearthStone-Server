package controller.response;

public class ShowBuyAndSellCardResponse extends Response {


    private String cardName;

    public ShowBuyAndSellCardResponse(String cardName) {
        setResponseType("ShowBuyAndSellCardResponse");
        this.cardName = cardName;
    }


    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
}
