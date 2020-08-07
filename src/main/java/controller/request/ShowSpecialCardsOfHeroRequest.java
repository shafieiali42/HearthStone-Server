package controller.request;

import Models.Cards.CardClasses.Cards;
import Models.Player.Player;
import controller.controllers.Administer;
import controller.controllers.CollectionController;
import controller.response.Response;
import controller.response.ShowSpecificCardsResponse;
import database.dssds;

import java.util.ArrayList;

public class ShowSpecialCardsOfHeroRequest extends Request {


    private String userName;


    public ShowSpecialCardsOfHeroRequest(String userName) {
        this.userName = userName;
    }


    @Override
    public Response execute() {
        Player player = dssds.fetchPlayer(userName);
        ArrayList<Cards> cards;
        cards = CollectionController.getCardsWithSpecificGroup(player.getDeckToChange().getHero().getName());
        ArrayList<String> names = Administer.giveListOfCardsNames(cards);
        Response response = new ShowSpecificCardsResponse(names,
                "SpecialCardsInCollections", "cardPanelOfDeckPage");

        return response;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
