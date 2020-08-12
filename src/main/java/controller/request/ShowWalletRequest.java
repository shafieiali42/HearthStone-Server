package controller.request;

import Models.Player.Player;
import controller.Status;
import controller.response.Response;
import controller.response.ShowWalletResponse;
import server.Server;
import utility.Log.Log;

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
        Log log =new Log(getUserName(),"SeeWallet");
        Server.getDataBaseHandler().save(log);
        Server.getDataBaseHandler().save(player);
        return response;
    }


}
