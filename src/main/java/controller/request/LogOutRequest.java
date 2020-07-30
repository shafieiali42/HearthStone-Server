package controller.request;

import controller.PlayerController;
import controller.response.LogOutResponse;
import controller.response.Response;

public class LogOutRequest extends Request {


    private String userName;


    public LogOutRequest(String sendersToken, String userName) {
        setRequestType("LogOutRequest");
        setRequestSendersToken(sendersToken);
        this.userName = userName;
    }

    @Override
    public Response execute() {
        Response response =new LogOutResponse(PlayerController.logOut());
        return response;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
