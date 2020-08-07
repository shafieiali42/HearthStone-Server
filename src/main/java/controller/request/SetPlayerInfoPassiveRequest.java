package controller.request;

import Models.Player.Player;
import controller.Status;
import controller.controllers.GamePartController;
import controller.response.GoToFirstThreeCardPageResponse;
import controller.response.Response;
import database.dssds;
import server.Server;


public class SetPlayerInfoPassiveRequest extends Request {


    private String userName;
    private int numberOfPassive;

    public SetPlayerInfoPassiveRequest(String userName,int numberOfPassive) {
        this.userName = userName;
        this.numberOfPassive=numberOfPassive;
    }

    @Override
    public Response execute() {
        Player player = dssds.fetchPlayer(userName);
        Response response = null;
        GamePartController.setFriendlyInfoPassiveOfGameState(Server.giveInGamePlayer(userName), numberOfPassive);

//                GameState.getInstance().setInfoPassive(GameState.getInstance().getPassivesToChoose().get(1));
        player.setPlayerStatusInGame(Status.FIRST_THREE_CARDS_PAGE);

        response = new GoToFirstThreeCardPageResponse
                (GamePartController.setNameOfFirstFriendlyThreeCards(Server.giveInGamePlayer(userName)));

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
