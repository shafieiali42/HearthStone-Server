package controller.request;

import Logic.PlayLogic.Game;
import Models.Player.Player;
import controller.response.Response;
import controller.response.ShowGameModesResponse;
import controller.response.ShowJOptionPaneResponse;
import database.dssds;

public class ShowGameModesRequest extends Request {


    private String userName;


    public ShowGameModesRequest(String userName) {
        this.userName = userName;
    }

    @Override
    public Response execute() {
        Player player = dssds.fetchPlayer(userName);
        Response response=null;
        if (player.getCurrentDeck() == null) {
            response=new ShowJOptionPaneResponse("First you should select your deck");
        } else {
            response = new ShowGameModesResponse(userName, Game.getGameModes());
        }
        return response;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
