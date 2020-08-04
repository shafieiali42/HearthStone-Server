package controller.request;

import Models.Player.Player;
import controller.Status;
import controller.controllers.GamePartController;
import controller.response.ChangeFirstThreeCardsResponse;
import controller.response.Response;
import database.DataBase;
import server.Server;

public class MousePressRequest extends Request {


    private String userName;
    private String cardName;
    private String firstCardNameOfThreeCards;
    private String secondCardNameOfThreeCards;
    private String thirdCardNameOfThreeCards;
    private boolean firstCardCanChangeInThreeCards;
    private boolean secondCardCanChangeInThreeCards;
    private boolean thirdCardCanChangeInThreeCards;


    public MousePressRequest(String userName, String cardName, String firstCardNameOfThreeCards, String secondCardNameOfThreeCards, String thirdCardNameOfThreeCards,
                             boolean firstCardCanChangeInThreeCards, boolean secondCardCanChangeInThreeCards,
                             boolean thirdCardCanChangeInThreeCards) {

        this.userName = userName;
        this.cardName = cardName;
        this.firstCardNameOfThreeCards = firstCardNameOfThreeCards;
        this.secondCardNameOfThreeCards = secondCardNameOfThreeCards;
        this.thirdCardNameOfThreeCards = thirdCardNameOfThreeCards;
        this.firstCardCanChangeInThreeCards = firstCardCanChangeInThreeCards;
        this.secondCardCanChangeInThreeCards = secondCardCanChangeInThreeCards;
        this.thirdCardCanChangeInThreeCards = thirdCardCanChangeInThreeCards;
    }

    @Override
    public Response execute() {
        Player player = DataBase.fetchPlayer(userName);
        Response response = null;

        if (player.getPlayerStatusInGame().equals(Status.FIRST_THREE_CARDS_PAGE)) {
            int changedCardIndex;
            changedCardIndex = GamePartController.ChangeThisCardFromHands(cardName, firstCardNameOfThreeCards, secondCardNameOfThreeCards,
                    thirdCardNameOfThreeCards, firstCardCanChangeInThreeCards,
                    secondCardCanChangeInThreeCards, thirdCardCanChangeInThreeCards, Server.giveInGamePlayer(userName));

            response = new ChangeFirstThreeCardsResponse(changedCardIndex,
                    GamePartController.setNameOfFirstFriendlyThreeCards(Server.giveInGamePlayer(userName)));

        }

        return response;
    }


    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getFirstCardNameOfThreeCards() {
        return firstCardNameOfThreeCards;
    }

    public void setFirstCardNameOfThreeCards(String firstCardNameOfThreeCards) {
        this.firstCardNameOfThreeCards = firstCardNameOfThreeCards;
    }

    public String getSecondCardNameOfThreeCards() {
        return secondCardNameOfThreeCards;
    }

    public void setSecondCardNameOfThreeCards(String secondCardNameOfThreeCards) {
        this.secondCardNameOfThreeCards = secondCardNameOfThreeCards;
    }

    public String getThirdCardNameOfThreeCards() {
        return thirdCardNameOfThreeCards;
    }

    public void setThirdCardNameOfThreeCards(String thirdCardNameOfThreeCards) {
        this.thirdCardNameOfThreeCards = thirdCardNameOfThreeCards;
    }

    public boolean isFirstCardCanChangeInThreeCards() {
        return firstCardCanChangeInThreeCards;
    }

    public void setFirstCardCanChangeInThreeCards(boolean firstCardCanChangeInThreeCards) {
        this.firstCardCanChangeInThreeCards = firstCardCanChangeInThreeCards;
    }

    public boolean isSecondCardCanChangeInThreeCards() {
        return secondCardCanChangeInThreeCards;
    }

    public void setSecondCardCanChangeInThreeCards(boolean secondCardCanChangeInThreeCards) {
        this.secondCardCanChangeInThreeCards = secondCardCanChangeInThreeCards;
    }

    public boolean isThirdCardCanChangeInThreeCards() {
        return thirdCardCanChangeInThreeCards;
    }

    public void setThirdCardCanChangeInThreeCards(boolean thirdCardCanChangeInThreeCards) {
        this.thirdCardCanChangeInThreeCards = thirdCardCanChangeInThreeCards;
    }
}
