package controller.request;

import Models.Player.Player;
import controller.Status;
import controller.response.GoToPageResponse;
import controller.response.Response;
import server.Server;

public class OkButtonDiscoverPageRequest extends Request {



    public OkButtonDiscoverPageRequest(String sendersToken, String userName) {
        setUserName(userName);
        setRequestType("OkButtonDiscoverPageRequest");

    }

    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(getUserName());
        Response response = null;
        player.setPlayerStatusInGame(Status.PLAY_PAGE);
        response = new GoToPageResponse("GamePage");
        Server.getDataBaseHandler().save(player);
        return response;
    }

}
