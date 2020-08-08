package controller.request;

import Models.Player.InGamePlayer;
import Models.Player.Player;
import controller.controllers.GamePartController;
import controller.response.Response;
import controller.response.ShowJOptionPaneResponse;
import server.Server;

public class EndTurnRequest extends Request {


    private String userName;


    public EndTurnRequest(String sendersToken,String userName) {
        setUserName(userName);
        setRequestType("DoneCreatDeckRequest");
        setRequestSendersToken(sendersToken);
        this.userName = userName;
    }


    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(userName);
        InGamePlayer whitePlayer = Server.giveInGamePlayer(userName);
        Response response = null;
        String message = GamePartController.endTurn(whitePlayer);
        if (message.equalsIgnoreCase("Successful")) {
            Request request = new ShowPlayPanelRequest(userName, "EndTurn");
            response = request.execute();
        } else {
            response = new ShowJOptionPaneResponse(message);
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
}
