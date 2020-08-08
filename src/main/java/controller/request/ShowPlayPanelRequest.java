package controller.request;

import Models.Player.InGamePlayer;
import Models.Player.Player;
import controller.Status;
import controller.controllers.GamePartController;
import controller.response.Response;
import controller.response.ShowPlayPanelResponse;
import server.Server;

import java.util.ArrayList;

public class ShowPlayPanelRequest extends Request {



    private String state;

    public ShowPlayPanelRequest(String userName, String state) {
        setUserName(userName);
        this.state = state;
    }

    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(getUserName());
        Response response = null;
        player.setPlayerStatusInGame(Status.PLAY_PAGE);
        InGamePlayer whitePlayer = Server.giveInGamePlayer(getUserName());
        InGamePlayer blackPlayer = Server.giveAnotherPlayer(getUserName());
        if (!Server.giveGameWithPlayer(getUserName()).getGameMode().equalsIgnoreCase("OfflineGame")) {
            ArrayList<String> whiteHandsCards = GamePartController.giveNameOfCardsList(whitePlayer.getHandsCards());
            ArrayList<String> whiteBattleGroundCards = GamePartController.giveNameOfCardsList(whitePlayer.getBattleGroundCards());
            response = new ShowPlayPanelResponse(getUserName(),whiteHandsCards, null, whiteBattleGroundCards,
                    null, whitePlayer.getHero().getName(), blackPlayer.getHero().getName(),
                    whitePlayer.getCurrentWeapon().getName(), blackPlayer.getCurrentWeapon().getName(), state);
        } else {
            ArrayList<String> whiteHandsCards = GamePartController.giveNameOfCardsList(whitePlayer.getHandsCards());
            ArrayList<String> whiteBattleGroundCards = GamePartController.giveNameOfCardsList(whitePlayer.getBattleGroundCards());
            ArrayList<String> blackHandsCards = GamePartController.giveNameOfCardsList(blackPlayer.getHandsCards());
            ArrayList<String> blackBattleGroundCards = GamePartController.giveNameOfCardsList(blackPlayer.getBattleGroundCards());
            response = new ShowPlayPanelResponse(getUserName(),whiteHandsCards, blackHandsCards, whiteBattleGroundCards,
                    blackBattleGroundCards, whitePlayer.getHero().getName(), blackPlayer.getHero().getName(),
                    whitePlayer.getCurrentWeapon().getName(), blackPlayer.getCurrentWeapon().getName(), state);
        }
        Server.getDataBaseHandler().save(player);
        return response;
    }

}
