package controller.request;

import Models.Player.Player;
import controller.Status;
import controller.response.Response;
import controller.response.ShowWalletResponse;
import server.Server;

public class ShowWalletRequest extends Request {


    private String userName;


    public ShowWalletRequest(String sendersToken, String userName) {
        setRequestType("ShowWalletRequest");
        setRequestSendersToken(sendersToken);
        this.userName = userName;
    }

    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(userName);
        player.setPlayerStatusInGame(Status.WALLET_PAGE);
        Response response =new ShowWalletResponse(player.getMoney());
        player.setPlayerStatusInGame(Status.BUY_PAGE);
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
