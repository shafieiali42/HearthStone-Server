package controller.request;

import Logic.PlayLogic.Game;
import Models.Player.InGamePlayer;
import Models.Player.Player;
import controller.response.QuitGameResponse;
import controller.response.Response;
import server.Server;
import utility.Log.Log;

public class QuitGameRequest extends Request {





    public QuitGameRequest(String userName) {
        setRequestType("QuitGameRequest");
        setUserName(userName);
    }

    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(getUserName());
        Response response=null;
        Game game = Server.giveGameWithPlayer(getUserName());
        player.setNumOfCups(player.getNumOfCups()-1);
        InGamePlayer anotherPlayer=Server.giveAnotherPlayer(getUserName());
        Player otherPlayer=anotherPlayer.getPlayer();
        otherPlayer.setNumOfCups(otherPlayer.getNumOfCups()+1);
        game.endGame();
        Server.getRunningGames().remove(game);
        response=new QuitGameResponse();
        Log log =new Log(getUserName(),"LeaveGame");
        Server.getDataBaseHandler().save(log);
        Server.sendResponse(otherPlayer.getUserName(),response);
        Server.getDataBaseHandler().save(otherPlayer);
        Server.getDataBaseHandler().save(player);
        return response;
    }

}
