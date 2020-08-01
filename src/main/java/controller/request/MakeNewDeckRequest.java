package controller.request;

import Models.Player.Player;
import controller.CollectionController;
import controller.Status;
import controller.response.MakeNewDeckResponse;
import controller.response.Response;
import database.DataBase;

public class MakeNewDeckRequest extends Request {


    private String userName;
    private String deckName;
    private String heroName;


    public MakeNewDeckRequest(String userName, String deckName, String heroName) {
        this.userName = userName;
        this.deckName = deckName;
        this.heroName = heroName;
    }

    @Override
    public Response execute() {
        Player player = DataBase.fetchPlayer(userName);
        player.setPlayerStatusInGame(Status.MAKE_DECK);
        CollectionController.makeNewDeck(player, deckName, heroName);
        Response response = new MakeNewDeckResponse(deckName, heroName);
        return response;
    }


    public String getDeckName() {
        return deckName;
    }

    public String getHeroName() {
        return heroName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }
}
