package controller.request;

import Models.Player.Player;
import controller.Status;
import controller.response.GoToPageResponse;
import controller.response.Response;
import server.Server;

public class OkButtonDiscoverPageRequest extends Request {

    private String userName;

    public OkButtonDiscoverPageRequest(String sendersToken,String userName) {
        this.userName = userName;
    }

    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(userName);
        Response response = null;
        player.setPlayerStatusInGame(Status.PLAY_PAGE);
        response = new GoToPageResponse("GamePage");
        Server.getDataBaseHandler().save(player);
        return response;
    }


    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
