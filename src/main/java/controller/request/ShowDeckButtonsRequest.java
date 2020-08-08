package controller.request;

import Models.Deck.Deck;
import Models.Player.Player;
import controller.response.Response;
import controller.response.ShowDeckButtonsResponse;
import server.Server;

import java.util.ArrayList;

public class ShowDeckButtonsRequest extends Request {



    public ShowDeckButtonsRequest(String userName) {
        setUserName(userName);
    }


    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(getUserName());
        Response response = null;

        ArrayList<String> listOfAllDeckOfPlayer = null;
        for (Deck deck : player.getAllDecksOfPlayer()) {
            listOfAllDeckOfPlayer.add(deck.getName());
        }
        response = new ShowDeckButtonsResponse(listOfAllDeckOfPlayer);
        Server.getDataBaseHandler().save(player);
        return response;
    }

}
