package controller.request;

import Models.Player.Player;
import controller.controllers.PlayerController;
import controller.response.LogOutResponse;
import controller.response.Response;
import server.Server;
import utility.Log.Log;

public class LogOutRequest extends Request {


    private boolean exit;

    public LogOutRequest(String sendersToken, String userName, boolean exit) {
        setUserName(userName);
        setRequestType("LogOutRequest");
        setRequestSendersToken(sendersToken);
        this.exit=exit;
    }

    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(getUserName());
        Response response = new LogOutResponse(PlayerController.logOut(player),exit);
        Log log =new Log(getUserName(),"LogOut");
        Server.getDataBaseHandler().save(log);
        Server.getDataBaseHandler().save(player);
        return response;
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }
}
