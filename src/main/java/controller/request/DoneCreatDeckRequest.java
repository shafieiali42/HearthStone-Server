package controller.request;

import Models.Player.Player;
import controller.Status;
import controller.response.DoneCreatDeckResponse;
import controller.response.Response;
import controller.response.ShowJOptionPaneResponse;
import server.Server;

public class DoneCreatDeckRequest extends Request {


    private String userName;

    public DoneCreatDeckRequest(String sendersToken,String userName) {
        setUserName(userName);
        setRequestType("DoneCreatDeckRequest");
        setRequestSendersToken(sendersToken);
        this.userName = userName;
    }

    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(userName);
        Response response=null;
        if (player.getDeckToChange().getListOfCards().size() < 15) {
            response=new ShowJOptionPaneResponse("You must select at least 15 cards.");
        } else {
            if (player.getPlayerStatusInGame().equals(Status.CHANGE_DECK)) {
//                ControllerOfMainComponents.currentPlayer.getLoggerOfMyPlayer().info("Changed deck " +
//                        deckPage.getNameOfDeckToChange());
                player.getDeckToChange().initUsesHashMapFromArrayList();
                player.setDeckToChange(null);
                response =new DoneCreatDeckResponse("CHANGE_DECK");
                player.setPlayerStatusInGame(Status.COLLECTIONS_PAGE);
            } else if (player.getPlayerStatusInGame().equals(Status.MAKE_DECK)) {
                player.getDeckToChange().initUsesHashMapFromArrayList();
                player.getAllDecksOfPlayer().add(player.getDeckToChange());
//                CollectionController.addCollectionStatesDeckToPlayersDecksList();
                player.setDeckToChange(null);
                response=new DoneCreatDeckResponse("MAKE_DECK");
                player.setPlayerStatusInGame( Status.COLLECTIONS_PAGE);
//                Administer.writeLog("Make new Deck");
            }
        }
        Server.getDataBaseHandler().save(player);
        return response;
    }
}
