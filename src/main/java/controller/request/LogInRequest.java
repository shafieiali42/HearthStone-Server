package controller.request;

import Models.Player.Player;
import controller.controllers.PlayerController;
import controller.response.LogInResponse;
import controller.response.Response;

public class LogInRequest extends Request {


    private String userName;
    private String password;
    private String mode;


    public LogInRequest(String sendersToken, String userName, String password, String mode) {
        setRequestType("LogInRequest");
        setRequestSendersToken(sendersToken);
        this.userName = userName;
        this.password = password;
        this.mode = mode;
    }

    @Override
    public Response execute() {
        Response response = null;
        if (mode.equalsIgnoreCase("SignIn")) {
            Player player=PlayerController.signIn(userName,password);
            if (player==null){
                response=new LogInResponse(false,"Invalid userName with this password");
            }else {
                response =new LogInResponse(true,player.getUserName());
            }
        } else if (mode.equalsIgnoreCase("SignUp")) {
            Player player=PlayerController.signUp(userName,password);
            if (player==null){
                response=new LogInResponse(false,"There is an user with this userName");
            }else {
                response =new LogInResponse(true,player.getUserName());
            }
        }
        return response;
    }


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

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
