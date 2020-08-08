package controller.request;

import Models.Player.Player;
import controller.Status;
import controller.response.Response;
import controller.response.ShowWalletResponse;
import server.Server;

public class ShowWalletRequest extends Request {





    public ShowWalletRequest(String sendersToken, String userName) {
        setRequestType("ShowWalletRequest");
        setUserName(userName);
        setRequestSendersToken(sendersToken);
    }

    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(getUserName());
        player.setPlayerStatusInGame(Status.WALLET_PAGE);
        Response response =new ShowWalletResponse(player.getMoney());
        player.setPlayerStatusInGame(Status.BUY_PAGE);
        Server.getDataBaseHandler().save(player);
        return response;
    }


}
