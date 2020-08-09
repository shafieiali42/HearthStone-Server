package controller.request;

import Models.Deck.Deck;
import Models.Player.Player;
import controller.Status;
import controller.response.Response;
import controller.response.ShowDeckResponse;
import server.Server;

import java.util.HashMap;

public class ShowDeckRequest extends Request {



    private String nameOfDeckToShow;


    public ShowDeckRequest(String userName, String nameOfDeckToShow) {
        setUserName(userName);
        this.nameOfDeckToShow = nameOfDeckToShow;
    }

    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(getUserName());
        Response response=null;
        player.setPlayerStatusInGame(Status.CHANGE_DECK);
        for (Deck deck:player.getAllDecksOfPlayer()){
            if (deck.getName().equalsIgnoreCase(nameOfDeckToShow)){
                player.setDeckToChange(deck);
            }
        }
        response=new ShowDeckResponse((HashMap<String, Integer>) player.getDeckToChange().getUsesHashMap());
        Server.getDataBaseHandler().save(player);
        return response;
    }

    public String getNameOfDeckToShow() {
        return nameOfDeckToShow;
    }

    public void setNameOfDeckToShow(String nameOfDeckToShow) {
        this.nameOfDeckToShow = nameOfDeckToShow;
    }
}
