package controller.request;

import Models.Player.Player;
import controller.controllers.CollectionController;
import controller.Status;
import controller.response.MakeNewDeckResponse;
import controller.response.Response;
import server.Server;

public class MakeNewDeckRequest extends Request {


    private String deckName;
    private String heroName;


    public MakeNewDeckRequest(String userName, String deckName, String heroName) {
        setUserName(userName);
        setRequestType("MakeNewDeckRequest");
        this.deckName = deckName;
        this.heroName = heroName;
    }

    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(getUserName());
        player.setPlayerStatusInGame(Status.MAKE_DECK);
        CollectionController.makeNewDeck(player, deckName, heroName);
        Server.getDataBaseHandler().save(player);
        Response response = new MakeNewDeckResponse(deckName);
        return response;
    }


    public String getDeckName() {
        return deckName;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }
}
