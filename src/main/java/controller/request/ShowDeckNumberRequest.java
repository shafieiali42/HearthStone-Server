package controller.request;

import Models.Deck.Deck;
import Models.Player.Player;
import controller.controllers.Administer;
import controller.response.Response;
import controller.response.ShowDeckNumberResponse;
import server.Server;

public class ShowDeckNumberRequest extends Request {


    private String userName;
    private int number;

    public ShowDeckNumberRequest(String sendersToken,String userName, int number) {
        this.userName = userName;
        this.number = number;
    }

    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(userName);
        Administer.sortDecksOfPlayer(player);
        Deck deck = player.getAllDecksOfPlayer().get(number - 1);
        String winsPerPlay;
        if (deck.getNumberOfUses()!=0){
            winsPerPlay =  (deck.getNumberOfWins() / deck.getNumberOfUses()) * 100 + " %";
        }else {
            winsPerPlay="0";
        }
        deck.defineManaAvg();
        deck.defineMostUsedCard();
        Response response = new ShowDeckNumberResponse(deck.getName(),deck.getHeroName(),
                deck.getNumberOfWins() + "",deck.getNumberOfUses() + "",
                deck.getMostUsedCard().getName(), deck.getManaAvg() + "", winsPerPlay + "");

        Server.getDataBaseHandler().save(player);
        return response;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
