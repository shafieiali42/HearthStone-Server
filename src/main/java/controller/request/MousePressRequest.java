package controller.request;

import Logic.PlayLogic.Alliance;
import Logic.PlayLogic.Game;
import Models.Player.Player;
import Visitors.CardVisitors.AfterSelectVisitor;
import controller.Status;
import controller.controllers.GamePartController;
import controller.response.ChangeFirstThreeCardsResponse;
import controller.response.DiscoverPageResponse;
import controller.response.Response;
import server.Server;

public class MousePressRequest extends Request {


    private String cardName;
    private String firstCardNameOfThreeCards;
    private String secondCardNameOfThreeCards;
    private String thirdCardNameOfThreeCards;
    private boolean firstCardCanChangeInThreeCards;
    private boolean secondCardCanChangeInThreeCards;
    private boolean thirdCardCanChangeInThreeCards;
    private Alliance alliance;


    public MousePressRequest(String userName, String cardName, String firstCardNameOfThreeCards,
                             String secondCardNameOfThreeCards, String thirdCardNameOfThreeCards,
                             boolean firstCardCanChangeInThreeCards, boolean secondCardCanChangeInThreeCards,
                             boolean thirdCardCanChangeInThreeCards, Alliance alliance) {

        setUserName(userName);
        setRequestType("MousePressRequest");
        this.cardName = cardName;
        this.firstCardNameOfThreeCards = firstCardNameOfThreeCards;
        this.secondCardNameOfThreeCards = secondCardNameOfThreeCards;
        this.thirdCardNameOfThreeCards = thirdCardNameOfThreeCards;
        this.firstCardCanChangeInThreeCards = firstCardCanChangeInThreeCards;
        this.secondCardCanChangeInThreeCards = secondCardCanChangeInThreeCards;
        this.thirdCardCanChangeInThreeCards = thirdCardCanChangeInThreeCards;
        this.alliance = alliance;
    }

    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(getUserName());
        Game game = Server.giveGameWithPlayer(getUserName());
        Response response = null;

        if (player.getPlayerStatusInGame().equals(Status.FIRST_THREE_CARDS_PAGE)) {
            int changedCardIndex;
            changedCardIndex = GamePartController.ChangeThisCardFromHands(cardName, firstCardNameOfThreeCards, secondCardNameOfThreeCards,
                    thirdCardNameOfThreeCards, firstCardCanChangeInThreeCards,
                    secondCardCanChangeInThreeCards, thirdCardCanChangeInThreeCards, Server.giveInGamePlayer(getUserName()));

            response = new ChangeFirstThreeCardsResponse(changedCardIndex,
                    GamePartController.setNameOfFirstFriendlyThreeCards(Server.giveInGamePlayer(getUserName())));

        } else if (player.getPlayerStatusInGame().equals(Status.DISCOVER_THREE_WEAPONS)) {
            response = new DiscoverPageResponse(cardName);
            game.setSelectedWeaponInDiscoverPage(cardName);
            GamePartController.getPlyingCardOfGameState(game).accept(new AfterSelectVisitor(),
                    GamePartController.getBattleGround(game),
                    GamePartController.getHandCards(game), GamePartController.getDeckCards(game),
                    null, null, null, null, alliance, game);
        }
        Server.getDataBaseHandler().save(player);
        return response;
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
