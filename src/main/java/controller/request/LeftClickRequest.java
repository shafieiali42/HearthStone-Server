package controller.request;

import Models.Player.Player;
import controller.Administer;
import controller.Status;
import controller.response.*;
import database.DataBase;

public class LeftClickRequest extends Request {


    private String userName;
    private String cardName;
    private boolean isLock;

    public LeftClickRequest(String userName, String cardName, boolean isLock) {
        this.userName = userName;
        this.cardName = cardName;
        this.isLock = isLock;
    }


    @Override
    public Response execute() {
        Player player = DataBase.fetchPlayer(userName);
        Response response = null;
        switch (player.getPlayerStatusInGame()) {

            case BUY_PAGE:
            case SELL_PAGE:
                response = new ShowBuyAndSellCardResponse(cardName);
                break;
            case COLLECTIONS_PAGE:
                if (this.isLock) {
                    player.setPlayerStatusInGame(Status.BUY_PAGE_FROM_COLLECTION);
                    response = new GoToPageResponse("ShopPage");
                } else {
                    response = new ShowJOptionPaneResponse("You can't Buy this card:((");
                }
                break;
            case MAKE_DECK:
            case CHANGE_DECK:

                Administer.addGivenCardToCollectionDeck(player, cardName, isLock);//todo
                response =new AddCardToDeckResponse();
                break;


        }
        return response;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public boolean isLock() {
        return isLock;
    }

    public void setLock(boolean lock) {
        isLock = lock;
    }
}
