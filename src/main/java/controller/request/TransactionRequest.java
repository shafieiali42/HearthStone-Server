package controller.request;

import Models.Player.Player;
import controller.controllers.Administer;
import controller.controllers.GamePartController;
import controller.response.Response;
import controller.response.ShowJOptionPaneResponse;
import controller.response.TransactionResponse;
import server.Server;

public class TransactionRequest extends Request {



    private String cardName;

    public TransactionRequest(String userName, String cardName) {
        setUserName(userName);
        this.cardName = cardName;
    }


    @Override
    public Response execute() {
        Response response = null;
        Player player = Server.getDataBaseHandler().fetchPlayer(getUserName());
        if (cardName == null) {
            response = new ShowJOptionPaneResponse("Please select a card!");
        } else {
            switch (player.getPlayerStatusInGame()) {
                case BUY_PAGE:
                    response = new TransactionResponse(Administer.buyShopStateCard(player, cardName), "Buy",
                            GamePartController.giveNameOfCardList(player.getAllCardsOfPlayer()));
                    break;
                case SELL_PAGE:
                    if (!Administer.isShopStateCardInMyDecks(player, cardName)) {
                        response = new TransactionResponse(Administer.sellShopStateCard(player, cardName), "Sell",
                                GamePartController.giveNameOfCardList(player.getAllCardsOfPlayer()));
                    } else {
                        response = new TransactionResponse(false,"sell",
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
