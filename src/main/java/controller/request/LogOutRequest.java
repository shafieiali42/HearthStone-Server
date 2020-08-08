package controller.request;

import Models.Player.Player;
import controller.controllers.PlayerController;
import controller.response.LogOutResponse;
import controller.response.Response;
import server.Server;

public class LogOutRequest extends Request {


    private String userName;


    public LogOutRequest(String sendersToken, String userName) {
        setRequestType("LogOutRequest");
        setRequestSendersToken(sendersToken);
        this.userName = userName;
    }

    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(userName);
        Response response =new LogOutResponse(PlayerController.logOut(player));
        Server.getDataBaseHandler().save(player);
        return response;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
