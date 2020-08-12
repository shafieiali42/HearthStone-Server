package controller.request;

import Logic.PlayLogic.Game;
import Models.Player.InGamePlayer;
import Models.Player.Player;
import com.google.gson.Gson;
import controller.Status;
import controller.controllers.GamePartController;
import controller.response.GoToPageResponse;
import controller.response.PLayGameResponse;
import controller.response.Response;
import server.Server;
import utility.Log.Log;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;


public class PlayGameRequest extends Request {


    private String gameMode;


    public PlayGameRequest(String userName, String gameMode) {
        setUserName(userName);
        this.gameMode = gameMode;
    }


    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(getUserName());
        Response response = null;
        ArrayList<Player> thisGameModePlayer = new ArrayList<>();


        if (gameMode.equalsIgnoreCase("OfflineGame")) {
            InGamePlayer inGamePlayer = new InGamePlayer(player, false);
            InGamePlayer enemy = new InGamePlayer(player, true);
            Game game = new Game(inGamePlayer, enemy, "OfflineGame");
            Server.getRunningGames().add(game);
            response = new PLayGameResponse(true, getUserName(), player.getCurrentHero().getName(),
                    "Mage", inGamePlayer.givePassiveToChooseNames());
            return response;
        }

        if (player.getCurrentDeck() == null) {
            player.setPlayerStatusInGame(Status.COLLECTION_PAGE_FROM_PLAY);
            response = new GoToPageResponse("CollectionPage");
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
        ArrayList<Player> sortedQueue = (ArrayList<Player>) thisGameModePlayer.clone();
        Collections.sort(sortedQueue);


        synchronized (Server.getPlayQueue()) { //todo maybe not :/
            Server.getPlayQueue().put(player, gameMode);
        }
        if (thisGameModePlayer.size() >= 2) {
            int indexOfStarter = GamePartController.giveIndexOfStarter(sortedQueue, thisGameModePlayer.get(0).getUserName());
            int secondPlayerIndex = 0;
            if (indexOfStarter != (sortedQueue.size() - 1)) {
                secondPlayerIndex = indexOfStarter + 1;
            } else if (indexOfStarter != 0) {
                secondPlayerIndex = indexOfStarter - 1;
            }
            int indexOfSecondPlayerInList = GamePartController.giveIndexOfStarter(thisGameModePlayer,
                    sortedQueue.get(secondPlayerIndex).getUserName());
            InGamePlayer whitePlayer = null;
            InGamePlayer blackPlayer = null;
            if (gameMode.equalsIgnoreCase("DeckReader")) {
                System.out.println("***********************************************");
                whitePlayer = new InGamePlayer(thisGameModePlayer.get(0), true);
                blackPlayer = new InGamePlayer(thisGameModePlayer.get(indexOfSecondPlayerInList), true);
            } else {
                whitePlayer = new InGamePlayer(thisGameModePlayer.get(0), false);
                blackPlayer = new InGamePlayer(thisGameModePlayer.get(indexOfSecondPlayerInList), false);//this player
            }
            Game game = new Game(whitePlayer, blackPlayer, gameMode);
            Server.getRunningGames().add(game);
            Server.getPlayQueue().remove(thisGameModePlayer.get(0));
            Server.getPlayQueue().remove(thisGameModePlayer.get(indexOfSecondPlayerInList));

            response = new PLayGameResponse(true, getUserName(), player.getCurrentHero().getName(),
                    whitePlayer.getHero().getName(), blackPlayer.givePassiveToChooseNames());


            try {

                Response responseForOtherPlayer = new PLayGameResponse(true, whitePlayer.getPlayer().getUserName(),
                        whitePlayer.getHero().getName(), blackPlayer.getHero().getName(), whitePlayer.givePassiveToChooseNames());
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
            thisGameModePlayer.remove(0);
            thisGameModePlayer.remove(0);
        } else {
            response = new PLayGameResponse(false, getUserName(), "", "", new ArrayList<>());//TODO NULLLLLLL
        }
        Log log =new Log(getUserName(),"PlayGameRequest");
        Server.getDataBaseHandler().save(log);
        Server.getDataBaseHandler().save(player);
        return response;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }
}
