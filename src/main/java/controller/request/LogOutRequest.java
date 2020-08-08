package controller.request;

import Models.Player.Player;
import controller.controllers.PlayerController;
import controller.response.LogOutResponse;
import controller.response.Response;
import server.Server;

public class LogOutRequest extends Request {





    public LogOutRequest(String sendersToken, String userName) {
        setUserName(userName);
        setRequestType("LogOutRequest");
        setRequestSendersToken(sendersToken);
    }

    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(getUserName());
        Response response =new LogOutResponse(PlayerController.logOut(player));
        Server.getDataBaseHandler().save(player);
        return response;
    }

}
