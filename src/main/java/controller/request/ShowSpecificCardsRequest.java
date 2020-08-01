package controller.request;

import Models.Cards.CardClasses.Cards;
import Models.Player.Player;
import controller.Administer;
import controller.response.Response;
import controller.response.ShowSpecificCardsResponse;
import database.DataBase;

import java.util.ArrayList;

public class ShowSpecificCardsRequest extends Request {


    private String group;
    private String userName;


    public ShowSpecificCardsRequest(String sendersToken, String userName, String group) {
        setRequestType("ShowSpecificCardsRequest");
        setRequestSendersToken(sendersToken);
        this.group = group;
        this.userName = userName;
    }

    @Override
    public Response execute() {
        Player player = DataBase.fetchPlayer(userName);
        ArrayList<Cards> cards = new ArrayList<>();
        switch (group) {
            case "Buyable":
                cards = player.getBuyableCards();
                break;
            case "Salable":
                cards = player.getSalableCards();
                break;
            case "":

                break;
            case "":

                break;
        }

        ArrayList<String> names = Administer.giveListOfCardsNames(cards);
        Response response = new ShowSpecificCardsResponse(names,group);
        return response;
    }


    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
