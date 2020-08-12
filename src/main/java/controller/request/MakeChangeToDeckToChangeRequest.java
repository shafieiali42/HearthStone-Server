package controller.request;

import Models.Cards.CardClasses.Cards;
import Models.Heroes.*;
import Models.Player.Player;
import controller.response.MakeChangeToDeckToChangeResponse;
import controller.response.Response;
import server.Server;

import java.util.ArrayList;

public class MakeChangeToDeckToChangeRequest extends Request {


    private String typeOfRequest;
    private String newFiled;


    public MakeChangeToDeckToChangeRequest(String sendersToken,String userName, String typeOfRequest, String newFiled) {
        setUserName(userName);
        setRequestType("MakeChangeToDeckToChangeRequest");
        setRequestSendersToken(sendersToken);
        this.typeOfRequest = typeOfRequest;
        this.newFiled = newFiled;
    }


    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(getUserName());
        Response response = null;
        switch (typeOfRequest) {
            case "SelectMainDeck":
                switch (player.getDeckToChange().getHero().getName()) {
                    case ("Mage"):
                        Mage mage = new Mage();
                        player.setMage(mage);
                        player.setCurrentHero(mage);
//                        CollectionState.getInstance().getDeckToChange().setHero(mage);
                        break;
                    case ("Rogue"):
                        Rogue rogue = new Rogue();
                        player.setRogue(rogue);
                        player.setCurrentHero(rogue);
//                        CollectionState.getInstance().getDeckToChange().setHero(rogue);
                        break;
                    case ("Warlock"):
                        Warlock warlock = new Warlock();
                        player.setWarlock(warlock);
                        player.setCurrentHero(warlock);
//                        CollectionState.getInstance().getDeckToChange().setHero(warlock);
                        break;
                    case ("Hunter"):
                        Hunter hunter = new Hunter();
                        player.setHunter(hunter);
                        player.setCurrentHero(hunter);
//                        CollectionState.getInstance().getDeckToChange().setHero(hunter);
                        break;
                    case ("Priest"):
                        Priest priest = new Priest();
                        player.setPriest(priest);
                        player.setCurrentHero(priest);
//                        CollectionState.getInstance().getDeckToChange().setHero(priest);
                        break;
                }
               player.setCurrentDeck(player.getDeckToChange());

                ArrayList<Cards> cards=new ArrayList<>();
                for (Cards cards1:player.getCurrentDeck().getListOfCards()){
                    for (Cards cards2:Cards.getAllCards()){
                        if (cards1.getName().equalsIgnoreCase(cards2.getName())){
                            cards.add(cards2.copy());
                        }
                    }
                }
                player.getCurrentDeck().setListOfCards(cards);
                response = new MakeChangeToDeckToChangeResponse(typeOfRequest,player.getCurrentDeck().getName());
                break;
            case "ChangeName":
                player.getDeckToChange().setName(newFiled);
                break;
            case "ChangeHero":
                switch (newFiled) {
                    case ("Mage"):
                        Mage mage = new Mage();
                        player.setMage(mage);
                        player.setCurrentHero(mage);
//                        CollectionState.getInstance().getDeckToChange().setHero(mage);
                        break;
                    case ("Rogue"):
                        Rogue rogue = new Rogue();
                        player.setRogue(rogue);
                        player.setCurrentHero(rogue);
//                        CollectionState.getInstance().getDeckToChange().setHero(rogue);
                        break;
                    case ("Warlock"):
                        Warlock warlock = new Warlock();
                        player.setWarlock(warlock);
                        player.setCurrentHero(warlock);
//                        CollectionState.getInstance().getDeckToChange().setHero(warlock);
                        break;
                    case ("Hunter"):
                        Hunter hunter = new Hunter();
                        player.setHunter(hunter);
                        player.setCurrentHero(hunter);
//                        CollectionState.getInstance().getDeckToChange().setHero(hunter);
                        break;
                    case ("Priest"):
                        Priest priest = new Priest();
                        player.setPriest(priest);
                        player.setCurrentHero(priest);
//                        CollectionState.getInstance().getDeckToChange().setHero(priest);
                        break;

                    default:
                        throw new IllegalStateException("Unexpected value: " + newFiled);
                }
                break;
            case "RemoveDeck":
                player.getAllDecksOfPlayer().remove(player.getDeckToChange());
                break;
        }
        Server.getDataBaseHandler().save(player);
        response=new MakeChangeToDeckToChangeResponse(typeOfRequest,newFiled);
        return response;
    }


    public String getTypeOfRequest() {
        return typeOfRequest;
    }

    public void setTypeOfRequest(String typeOfRequest) {
        this.typeOfRequest = typeOfRequest;
    }

    public String getNewFiled() {
        return newFiled;
    }

    public void setNewFiled(String newFiled) {
        this.newFiled = newFiled;
    }
}
