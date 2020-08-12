package controller.request;

import Models.Player.Player;
import controller.controllers.Administer;
import controller.controllers.GamePartController;
import controller.response.Response;
import controller.response.ShowJOptionPaneResponse;
import controller.response.BuyOrSellResponse;
import server.Server;
import utility.Log.Log;

public class BuyOrSellRequest extends Request {


    private String cardName;

    public BuyOrSellRequest(String userName, String cardName) {
        setRequestType("BuyOrSellRequest");
        setUserName(userName);
        this.cardName = cardName;
    }


    @Override
    public Response execute() {
        Response response = null;
        Player player = Server.getDataBaseHandler().fetchPlayer(getUserName());
        Log log = null;

        if (cardName == null) {
            response = new ShowJOptionPaneResponse("Please select a card!");
        } else {
            switch (player.getPlayerStatusInGame()) {
                case BUY_PAGE:
                    response = new BuyOrSellResponse(Administer.buyShopStateCard(player, cardName), "Buy",
                            GamePartController.giveNameOfCardList(player.getAllCardsOfPlayer()));
//                    log = new Log(getUserName(), "BuyCardRequest");
//                    Server.getDataBaseHandler().save(log);
                    break;
                case SELL_PAGE:
                    if (!Administer.isShopStateCardInMyDecks(player, cardName)) {
                        response = new BuyOrSellResponse(Administer.sellShopStateCard(player, cardName), "Sell",
                                GamePartController.giveNameOfCardList(player.getAllCardsOfPlayer()));
                        log = new Log(getUserName(), "SellCardRequest");
                        Server.getDataBaseHandler().save(log);
                    } else {
                        response = new BuyOrSellResponse(false, "sell",
                                GamePartController.giveNameOfCardList(player.getAllCardsOfPlayer()));
                    }
                    break;
            }
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
}
