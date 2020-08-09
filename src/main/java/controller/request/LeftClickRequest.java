package controller.request;

import Models.Player.Player;
import controller.controllers.Administer;
import controller.Status;
import controller.response.*;
import server.Server;

import java.util.HashMap;

public class LeftClickRequest extends Request {



    private String cardName;
    private boolean isLock;


    public LeftClickRequest(String sendersToken,String userName, String cardName, boolean isLock) {
        setUserName(userName);
        setRequestType("LeftClickRequest");
        setRequestSendersToken(sendersToken);

        this.cardName = cardName;
        this.isLock = isLock;
    }


    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(getUserName());
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
                response =new AddCardToDeckResponse(player.getUserName(), (HashMap<String, Integer>) player.getDeckToChange().getUsesHashMap());
                break;


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

    public boolean isLock() {
        return isLock;
    }

    public void setLock(boolean lock) {
        isLock = lock;
    }
}
