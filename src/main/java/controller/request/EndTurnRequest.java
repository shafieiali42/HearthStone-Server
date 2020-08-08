package controller.request;

import Models.Player.InGamePlayer;
import Models.Player.Player;
import controller.controllers.GamePartController;
import controller.response.Response;
import controller.response.ShowJOptionPaneResponse;
import server.Server;

public class EndTurnRequest extends Request {





    public EndTurnRequest(String sendersToken,String userName) {
        setUserName(userName);
        setRequestType("EndTurnRequest");
        setRequestSendersToken(sendersToken);

    }


    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(getUserName());
        InGamePlayer whitePlayer = Server.giveInGamePlayer(getUserName());
        Response response = null;
        String message = GamePartController.endTurn(whitePlayer);
        if (message.equalsIgnoreCase("Successful")) {
            Request request = new ShowPlayPanelRequest(getUserName(), "EndTurn");
            response = request.execute();
        } else {
            response = new ShowJOptionPaneResponse(message);
        }
        Server.getDataBaseHandler().save(player);
        return response;
    }


}
