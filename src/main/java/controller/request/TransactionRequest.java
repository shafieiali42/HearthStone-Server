package controller.request;

import Models.Player.Player;
import controller.controllers.Administer;
import controller.response.Response;
import controller.response.ShowJOptionPaneResponse;
import controller.response.TransactionResponse;
import server.Server;

public class TransactionRequest extends Request {


    private String userName;
    private String cardName;

    public TransactionRequest(String sendersToken,String userName, String cardName) {
        this.userName = userName;
        this.cardName = cardName;
    }


    @Override
    public Response execute() {
        Response response = null;
        Player player = Server.getDataBaseHandler().fetchPlayer(userName);
        if (cardName == null) {
            response = new ShowJOptionPaneResponse("Please select a card!");
        } else {
            switch (player.getPlayerStatusInGame()) {
                case BUY_PAGE:
                    response = new TransactionResponse(Administer.buyShopStateCard(player, cardName), "Buy");
                    break;
                case SELL_PAGE:
                    if (!Administer.isShopStateCardInMyDecks(player, cardName)) {
                        response = new TransactionResponse(Administer.sellShopStateCard(player, cardName), "Sell");
                    } else {
                        response = new TransactionResponse(false,"sell");
                    }
                    break;
            }
        }
        Server.getDataBaseHandler().save(player);
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


}
