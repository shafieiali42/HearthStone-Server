package controller.request;

import Models.Player.Player;
import controller.Status;
import controller.controllers.PlayerController;
import controller.response.LogInResponse;
import controller.response.Response;
import server.Server;

public class LogInRequest extends Request {


    private String password;
    private String mode;


    public LogInRequest(String sendersToken, String userName, String password, String mode) {
        setUserName(userName);
        setRequestType("LogInRequest");
        setRequestSendersToken(sendersToken);
        this.password = password;
        this.mode = mode;
    }

    @Override
    public Response execute() {
        Response response = null;
        if (mode.equalsIgnoreCase("SignIn")) {
            Player player=PlayerController.signIn(getUserName(),password);
            if (player==null){
                response=new LogInResponse(false,"Invalid userName with this password");
            }else {
                player.setPlayerStatusInGame(Status.MAIN_MENU_PAGE);
                response =new LogInResponse(true,player.getUserName());
            }
            Server.getDataBaseHandler().save(player);
        } else if (mode.equalsIgnoreCase("SignUp")) {
            Player player=PlayerController.signUp(getUserName(),password);
            if (player==null){
                response=new LogInResponse(false,"There is an user with this userName");
            }else {
                player.setPlayerStatusInGame(Status.MAIN_MENU_PAGE);
                response =new LogInResponse(true,player.getUserName());
                Server.getDataBaseHandler().save(player);
            }

        }
        return response;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
