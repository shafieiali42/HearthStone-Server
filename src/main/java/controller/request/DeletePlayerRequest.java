package controller.request;

import Models.Player.Player;
import controller.controllers.PlayerController;
import controller.response.DeletePlayerResponse;
import controller.response.Response;
import server.Server;
import utility.Log.Log;

public class DeletePlayerRequest extends Request {


    String password;

    public DeletePlayerRequest(String sendersToken,String userName, String password) {
        setUserName(userName);
        setRequestType("DeletePlayerRequest");
        setRequestSendersToken(sendersToken);

        this.password = password;
    }

    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(getUserName());
        Log log =new Log(getUserName(),"Deleted");
        Server.getDataBaseHandler().save(log);
        Response response=new DeletePlayerResponse(PlayerController.deletePlayer(getUserName(),password,player));
//        Server.getDataBaseHandler().save(player);
        return response;
    }

    //getter and setters
    //********************


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
