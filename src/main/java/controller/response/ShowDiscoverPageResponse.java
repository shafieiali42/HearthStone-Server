package controller.response;

public class ShowDiscoverPageResponse extends Response {

    private String firstCard;
    private String secondCard;
    private String thirdCard;

    public ShowDiscoverPageResponse(String firstCard, String secondCard, String thirdCard) {
        setResponseType("ShowDiscoverPageResponse");
        this.firstCard = firstCard;
        this.secondCard = secondCard;
        this.thirdCard = thirdCard;
    }


    public String getFirstCard() {
        return firstCard;
    }

    public void setFirstCard(String firstCard) {
        this.firstCard = firstCard;
    }

    public String getSecondCard() {
        return secondCard;
    }

    public void setSecondCard(String secondCard) {
        this.secondCard = secondCard;
    }

    public String getThirdCard() {
        return thirdCard;
    }

    public void setThirdCard(String thirdCard) {
        this.thirdCard = thirdCard;
    }
}
