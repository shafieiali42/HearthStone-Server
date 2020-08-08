package controller.request;

import Models.Player.Player;
import controller.Status;
import controller.controllers.GamePartController;
import controller.response.GoToFirstThreeCardPageResponse;
import controller.response.GoToPageResponse;
import controller.response.Response;
import server.Server;


public class SetPlayerInfoPassiveRequest extends Request {


    private String userName;
    private int numberOfPassive;

    public SetPlayerInfoPassiveRequest(String userName, int numberOfPassive) {
        this.userName = userName;
        this.numberOfPassive = numberOfPassive;
    }

    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(userName);
        Response response = null;

        GamePartController.setFriendlyInfoPassiveOfGameState(Server.giveInGamePlayer(userName), numberOfPassive);
        if (!Server.giveGameWithPlayer(userName).getGameMode().equalsIgnoreCase("DeckReader")) {
            player.setPlayerStatusInGame(Status.FIRST_THREE_CARDS_PAGE);
            response = new GoToFirstThreeCardPageResponse
                    (GamePartController.setNameOfFirstFriendlyThreeCards(Server.giveInGamePlayer(userName)));
        } else {
            player.setPlayerStatusInGame(Status.PLAY_PAGE);
            response = new GoToPageResponse("GamePage");
        }

        Server.getDataBaseHandler().save(player);
        return response;
    }


    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getNumberOfPassive() {
        return numberOfPassive;
    }

    public void setNumberOfPassive(int numberOfPassive) {
        this.numberOfPassive = numberOfPassive;
    }
}
