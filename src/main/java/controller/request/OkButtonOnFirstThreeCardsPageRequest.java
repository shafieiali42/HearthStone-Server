package controller.request;

import Models.Player.InGamePlayer;
import Models.Player.Player;
import controller.Status;
import controller.controllers.GamePartController;
import controller.response.Response;
import controller.response.ShowPlayPanelResponse;
import server.Server;

import java.util.ArrayList;

public class OkButtonOnFirstThreeCardsPageRequest extends Request {

    private String userName;

    public OkButtonOnFirstThreeCardsPageRequest(String userName) {
        this.userName = userName;
    }

    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(userName);
        Response response = null;
        player.setPlayerStatusInGame(Status.PLAY_PAGE);
        InGamePlayer whitePlayer = Server.giveInGamePlayer(userName);
        InGamePlayer blackPlayer = Server.giveAnotherPlayer(userName);
        if (!Server.giveGameWithPlayer(userName).getGameMode().equalsIgnoreCase("OfflineGame")) {
            ArrayList<String> whiteHandsCards = GamePartController.giveNameOfCardsList(whitePlayer.getHandsCards());
            ArrayList<String> whiteBattleGroundCards = GamePartController.giveNameOfCardsList(whitePlayer.getBattleGroundCards());
            response = new ShowPlayPanelResponse(userName, whiteHandsCards, null, whiteBattleGroundCards,
                    null, whitePlayer.getHero().getName(), blackPlayer.getHero().getName(),
                    whitePlayer.getCurrentWeapon().getName(), blackPlayer.getCurrentWeapon().getName(),"StartGame");
        }else {
            ArrayList<String> whiteHandsCards = GamePartController.giveNameOfCardsList(whitePlayer.getHandsCards());
            ArrayList<String> whiteBattleGroundCards = GamePartController.giveNameOfCardsList(whitePlayer.getBattleGroundCards());
            ArrayList<String> blackHandsCards = GamePartController.giveNameOfCardsList(blackPlayer.getHandsCards());
            ArrayList<String> blackBattleGroundCards = GamePartController.giveNameOfCardsList(blackPlayer.getBattleGroundCards());
            response = new ShowPlayPanelResponse(userName, whiteHandsCards, blackHandsCards, whiteBattleGroundCards,
                    blackBattleGroundCards, whitePlayer.getHero().getName(), blackPlayer.getHero().getName(),
                    whitePlayer.getCurrentWeapon().getName(), blackPlayer.getCurrentWeapon().getName(),"StartGame");
        }
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
