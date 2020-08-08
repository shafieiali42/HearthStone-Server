package controller.request;

import Logic.PlayLogic.Game;
import Models.Player.Player;
import controller.response.QuitGameResponse;
import controller.response.Response;
import server.Server;

public class QuitGameRequest extends Request {


    private String userName;


    public QuitGameRequest(String userName) {
        this.userName = userName;
    }

    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(userName);
        Response response=null;
        Game game = Server.giveGameWithPlayer(userName);
        Server.getRunningGames().remove(game);
        response=new QuitGameResponse();
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
