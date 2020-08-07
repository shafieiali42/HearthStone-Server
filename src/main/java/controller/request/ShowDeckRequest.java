package controller.request;

import Models.Deck.Deck;
import Models.Player.Player;
import controller.Status;
import controller.response.Response;
import controller.response.ShowDeckResponse;
import database.dssds;

public class ShowDeckRequest extends Request {


    private String userName;
    private String nameOfDeckToShow;


    public ShowDeckRequest(String userName, String nameOfDeckToShow) {
        this.userName = userName;
        this.nameOfDeckToShow = nameOfDeckToShow;
    }

    @Override
    public Response execute() {
        Player player = dssds.fetchPlayer(userName);
        Response response=null;
        player.setPlayerStatusInGame(Status.CHANGE_DECK);
        for (Deck deck:player.getAllDecksOfPlayer()){
            if (deck.getName().equalsIgnoreCase(nameOfDeckToShow)){
                player.setDeckToChange(deck);
            }
        }
        response=new ShowDeckResponse(player.getDeckToChange().getUsesHashMap());
        return response;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNameOfDeckToShow() {
        return nameOfDeckToShow;
    }

    public void setNameOfDeckToShow(String nameOfDeckToShow) {
        this.nameOfDeckToShow = nameOfDeckToShow;
    }
}
