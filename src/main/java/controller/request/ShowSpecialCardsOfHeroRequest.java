package controller.request;

import Models.Cards.CardClasses.Cards;
import Models.Player.Player;
import controller.controllers.Administer;
import controller.controllers.CollectionController;
import controller.response.Response;
import controller.response.ShowSpecificCardsResponse;
import server.Server;
import utility.Log.Log;

import java.util.ArrayList;

public class ShowSpecialCardsOfHeroRequest extends Request {





    public ShowSpecialCardsOfHeroRequest(String userName) {
        setUserName(userName);
    }


    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(getUserName());
        ArrayList<Cards> cards;
        cards = CollectionController.getCardsWithSpecificGroup(player.getDeckToChange().getHero().getName());
        ArrayList<String> names = Administer.giveListOfCardsNames(cards);
        Response response = new ShowSpecificCardsResponse(names,
                "SpecialCardsInCollections", "cardPanelOfDeckPage");

        Log log =new Log(getUserName(),"SeeSpecialCards");
        Server.getDataBaseHandler().save(log);
        Server.getDataBaseHandler().save(player);
        return response;
    }

}
