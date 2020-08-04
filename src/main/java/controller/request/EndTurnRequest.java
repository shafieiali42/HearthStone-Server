package controller.request;

import Models.Player.InGamePlayer;
import Models.Player.Player;
import controller.controllers.GamePartController;
import controller.controllers.Mapper;
import controller.response.Response;
import controller.response.ShowJOptionPaneResponse;
import controller.response.ShowPlayPanelResponse;
import database.DataBase;
import server.Server;

import java.util.ArrayList;

public class EndTurnRequest extends Request {


    private String userName;


    public EndTurnRequest(String userName) {
        this.userName = userName;
    }


    @Override
    public Response execute() {
        Player player = DataBase.fetchPlayer(userName);
        InGamePlayer whitePlayer = Server.giveInGamePlayer(userName);
        Response response = null;
        String message = GamePartController.endTurn(whitePlayer);
        if (message.equalsIgnoreCase("Successful")) {
            Request request = new ShowPlayPanelRequest(userName, "EndTurn");
            response = request.execute();
        } else {
            response = new ShowJOptionPaneResponse(message);
        }
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
}
