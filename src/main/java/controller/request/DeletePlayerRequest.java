package controller.request;

import Models.Player.Player;
import controller.controllers.PlayerController;
import controller.response.DeletePlayerResponse;
import controller.response.Response;
import server.Server;

public class DeletePlayerRequest extends Request {

    String userName;
    String password;

    public DeletePlayerRequest(String sendersToken,String userName, String password) {
        setUserName(userName);
        setRequestType("LogInRequest");
        setRequestSendersToken(sendersToken);
        this.userName = userName;
        this.password = password;
    }

    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(userName);
        Response response=new DeletePlayerResponse(PlayerController.deletePlayer(userName,password,player));
//        Server.getDataBaseHandler().save(player);
        return response;
    }

    //getter and setters
    //********************

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
