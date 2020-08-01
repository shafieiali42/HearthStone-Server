package controller.request;

import Models.Player.Player;
import controller.response.Response;
import controller.response.ShowWalletResponse;
import database.DataBase;

public class ShowWalletRequest extends Request {


    private String userName;


    public ShowWalletRequest(String sendersToken, String userName) {
        setRequestType("ShowWalletRequest");
        setRequestSendersToken(sendersToken);
        this.userName = userName;
    }

    @Override
    public Response execute() {
        Player player = DataBase.fetchPlayer(userName);
        Response response =new ShowWalletResponse(player.getMoney());
        return response;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
