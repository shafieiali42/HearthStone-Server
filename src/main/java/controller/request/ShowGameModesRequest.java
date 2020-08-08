package controller.request;

import Logic.PlayLogic.Game;
import Models.Player.Player;
import controller.response.Response;
import controller.response.ShowGameModesResponse;
import controller.response.ShowJOptionPaneResponse;
import server.Server;

public class ShowGameModesRequest extends Request {




    public ShowGameModesRequest(String userName) {
        setUserName(userName);
    }

    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(getUserName());
        Response response=null;
        if (player.getCurrentDeck() == null) {
            response=new ShowJOptionPaneResponse("First you should select your deck");
        } else {
            response = new ShowGameModesResponse(getUserName(),Game.getGameModes());
        }
        Server.getDataBaseHandler().save(player);
        return response;
    }

}
