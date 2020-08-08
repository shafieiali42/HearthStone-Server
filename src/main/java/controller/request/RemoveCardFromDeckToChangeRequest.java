package controller.request;

import Models.Cards.CardClasses.Cards;
import Models.Player.Player;
import controller.response.RemoveCardFromDeckToChangeResponse;
import controller.response.Response;
import server.Server;

import java.util.Iterator;

public class RemoveCardFromDeckToChangeRequest extends Request {


    private String userName;
    private String cardName;


    public RemoveCardFromDeckToChangeRequest(String userName, String cardName) {
        this.userName = userName;
        this.cardName = cardName;
    }

    @Override
    public Response execute() { //todo
        Player player = Server.getDataBaseHandler().fetchPlayer(userName);
        Iterator<Cards> itr = player.getDeckToChange().getListOfCards().iterator();
        while (itr.hasNext()) {
            Cards card = itr.next();
            if (card.getName().equalsIgnoreCase(cardName)) {
                for (String cardNameInMap : player.getDeckToChange().getUsesHashMap().keySet()) {
                    if (cardNameInMap.equalsIgnoreCase(cardName)) {
                        if (player.getDeckToChange().getUsesHashMap().get(cardNameInMap) == 1) {
                            itr.remove();
                            player.getDeckToChange().getUsesHashMap().put(cardNameInMap, 0);
//                    DeckViewer.getInstance().showCardsInDecK();
                            break;
                        } else if (player.getDeckToChange().getUsesHashMap().get(cardNameInMap) == 2) {
                            player.getDeckToChange().getUsesHashMap().put(cardNameInMap, 1);
                        }
//                            littleCardPanel.getUsedLabel().setText(1 + "");
                        break;
                    }
                }
            }
        }
        Server.getDataBaseHandler().save(player);
        Response response = new RemoveCardFromDeckToChangeResponse(userName, player.getDeckToChange().getUsesHashMap());
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
