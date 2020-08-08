package controller.request;

import Logic.PlayLogic.Game;
import Models.Player.Player;
import controller.response.QuitGameResponse;
import controller.response.Response;
import server.Server;

public class QuitGameRequest extends Request {





    public QuitGameRequest(String userName) {
        setUserName(userName);
    }

    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(getUserName());
        Response response=null;
        Game game = Server.giveGameWithPlayer(getUserName());
        Server.getRunningGames().remove(game);
        response=new QuitGameResponse();
        Server.getDataBaseHandler().save(player);
        return response;
    }

}
