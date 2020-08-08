package controller.request;

import Models.Cards.CardClasses.Cards;
import Models.Player.Player;
import controller.Status;
import controller.response.Response;
import controller.response.ShowSpecificCardsResponse;
import server.Server;

import java.util.ArrayList;

public class ShowSearchCardsRequest extends Request {

    private String userName;
    private String searchTextField;


    public ShowSearchCardsRequest(String sendersToken,String userName, String searchTextField) {
        this.userName = userName;
        this.searchTextField = searchTextField;
    }

    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(userName);
        Response response = null;
        ArrayList<String> foundCards = new ArrayList<String>();
        for (Cards card : Cards.getAllCards()) {
            if (card.getName().toLowerCase().contains(searchTextField)) {
                foundCards.add(card.getName());
            }
        }
        if (player.getPlayerStatusInGame().equals(Status.COLLECTIONS_PAGE)) {
            response = new ShowSpecificCardsResponse(foundCards,"Search",
                    "cardPanelOfCollectionPage");
        } else if (player.getPlayerStatusInGame().equals(Status.CHANGE_DECK) ||
                player.getPlayerStatusInGame().equals(Status.MAKE_DECK)) {
            response = new ShowSpecificCardsResponse(foundCards,"Search",
                    "cardPanelOfDeckPage");

        }
        Server.getDataBaseHandler().save(player);
        return response;
    }


    public String getSearchTextField() {
        return searchTextField;
    }

    public void setSearchTextField(String searchTextField) {
        this.searchTextField = searchTextField;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
