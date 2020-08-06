package controller.request;

import Models.Player.Player;
import controller.Status;
import controller.response.GoToPageResponse;
import controller.response.Response;
import database.DataBase;

public class OkButtonDiscoverPageRequest extends Request {

    private String userName;

    public OkButtonDiscoverPageRequest(String userName) {
        this.userName = userName;
    }

    @Override
    public Response execute() {
        Player player = DataBase.fetchPlayer(userName);
        Response response = null;
        player.setPlayerStatusInGame(Status.PLAY_PAGE);
        response = new GoToPageResponse("GamePage");
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
