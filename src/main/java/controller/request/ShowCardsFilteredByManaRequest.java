package controller.request;

import Models.Cards.CardClasses.Cards;
import Models.Player.Player;
import controller.Status;
import controller.response.Response;
import controller.response.ShowSpecificCardsResponse;
import server.Server;

import java.util.ArrayList;

public class ShowCardsFilteredByManaRequest extends Request {

    private String userName;
    private int mana;

    public ShowCardsFilteredByManaRequest(String userName, int mana) {
        this.userName = userName;
        this.mana = mana;
    }

    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(userName);
        Response response = null;
        ArrayList<String> filteredByManaCards = new ArrayList<String>();
        for (Cards card : Cards.getAllCards()) {
            if (card.getManaCost() == mana) {
                filteredByManaCards.add(card.getName());
            }
        }
        if (player.getPlayerStatusInGame().equals(Status.COLLECTIONS_PAGE)) {
            response = new ShowSpecificCardsResponse(filteredByManaCards, "Mana", "cardPanelOfCollectionPage");

        } else if (player.getPlayerStatusInGame().equals(Status.MAKE_DECK) ||
                player.getPlayerStatusInGame().equals(Status.CHANGE_DECK)) {
            response = new ShowSpecificCardsResponse(filteredByManaCards, "Mana", "cardPanelOfDeckPage");
        }
//        ControllerOfMainComponents.currentPlayer.getLoggerOfMyPlayer().info("Show cards with mana: " + mana);
        Server.getDataBaseHandler().save(player);
        return response;
    }


    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
