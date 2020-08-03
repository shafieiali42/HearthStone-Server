package controller.request;

import Models.Cards.CardClasses.Cards;
import Models.Player.Player;
import controller.controllers.Administer;
import controller.controllers.CollectionController;
import controller.response.Response;
import controller.response.ShowSpecificCardsResponse;
import database.DataBase;

import java.util.ArrayList;

public class ShowSpecificCardsRequest extends Request {


    private String group;
    private String userName;
    private String panelName;


    public ShowSpecificCardsRequest(String sendersToken, String userName, String group,String panelName) {
        setRequestType("ShowSpecificCardsRequest");
        setRequestSendersToken(sendersToken);
        this.group = group;
        this.userName = userName;
        this.panelName=panelName;
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
            case "LockCards":
                cards = player.getLockCards();
                break;
            case "UnLockCards":
                cards =player.getAllCardsOfPlayer();
                break;
            case "AllCards":
                cards = Cards.getAllCards();
                break;
            case "NeutralCards":
                cards = CollectionController.getCardsWithSpecificGroup("Neutral");
                break;
            case "PriestCards":
                cards = CollectionController.getCardsWithSpecificGroup("Priest");
                break;
            case "HunterCards":
                cards = CollectionController.getCardsWithSpecificGroup("Hunter");
                break;
            case "WarlockCards":
                cards = CollectionController.getCardsWithSpecificGroup("Warlock");
                break;
            case "RogueCards":
                cards = CollectionController.getCardsWithSpecificGroup("Rogue");
                break;
            case "MageCards":
                cards = CollectionController.getCardsWithSpecificGroup("Mage");
                break;
        }

        ArrayList<String> names = Administer.giveListOfCardsNames(cards);
        Response response = new ShowSpecificCardsResponse(names,group,panelName);
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

    public String getPanelName() {
        return panelName;
    }

    public void setPanelName(String panelName) {
        this.panelName = panelName;
    }
}
