package controller.request;

import Logic.PlayLogic.Game;
import Models.Player.InGamePlayer;
import Models.Player.Player;
import com.google.gson.Gson;
import controller.Status;
import controller.response.GoToPageResponse;
import controller.response.PLayGameResponse;
import controller.response.Response;
import server.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class PlayGameRequest extends Request {

    private String userName;
    private String gameMode;


    public PlayGameRequest(String sendersToken,String userName, String gameMode) {
        this.userName = userName;
        this.gameMode = gameMode;
    }


    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(userName);
        Response response = null;
        ArrayList<Player> thisGameModePlayer = new ArrayList<>();

        if (player.getCurrentDeck()==null){
            player.setPlayerStatusInGame(Status.COLLECTION_PAGE_FROM_PLAY);
            response =new GoToPageResponse("CollectionPage");
            Server.getDataBaseHandler().save(player);
            return response;
        }

        player.setPlayerStatusInGame(Status.PLAY_PAGE); //todoooo


        for (Player player1 : Server.getPlayQueue().keySet()) {
            if (Server.getPlayQueue().get(player1).equalsIgnoreCase(gameMode)) {
                thisGameModePlayer.add(player1);
            }
        }
        thisGameModePlayer.add(player);
        synchronized (Server.getPlayQueue()) { //todo maybe not :/
            Server.getPlayQueue().put(player, gameMode);
        }
        if (thisGameModePlayer.size() >= 2) {
            InGamePlayer whitePlayer = new InGamePlayer(thisGameModePlayer.get(0), false);
            InGamePlayer blackPlayer = new InGamePlayer(thisGameModePlayer.get(1), false);//this player
            Game game = new Game(whitePlayer, blackPlayer);
            Server.getRunningGames().add(game);
            Server.getPlayQueue().remove(thisGameModePlayer.get(0));
            Server.getPlayQueue().remove(thisGameModePlayer.get(1));

            response = new PLayGameResponse(true,userName, player.getCurrentHero().getName(),
                    whitePlayer.getHero().getName(),blackPlayer.givePassiveToChooseNames());


            try {

                Response responseForOtherPlayer = new PLayGameResponse(true,whitePlayer.getPlayer().getUserName(),
                        whitePlayer.getHero().getName(),blackPlayer.getHero().getName(),whitePlayer.givePassiveToChooseNames());
                String message = new Gson().toJson(responseForOtherPlayer);
                PrintWriter printer;
                printer = new PrintWriter(Server.giveSocketWithUserName(whitePlayer.getPlayer().getUserName()).getOutputStream());
                printer.println(responseForOtherPlayer.getResponseReceiversToken());
                printer.println(responseForOtherPlayer.getResponseType());
                printer.println(message);
                printer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            response = new PLayGameResponse(false,userName, null, null,null);
        }
        Server.getDataBaseHandler().save(player);
        return response;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }
}
