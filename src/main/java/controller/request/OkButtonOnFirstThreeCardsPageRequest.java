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



    public OkButtonOnFirstThreeCardsPageRequest(String userName) {

        setUserName(userName);
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

            response = new ShowPlayPanelResponse(getUserName(),whiteHandsCards, new ArrayList<>(), whiteBattleGroundCards,
                    new ArrayList<>(), whitePlayer.getHeroesName(), blackPlayer.getHeroesName(),
                    whitePlayer.getCurrentWeaponsName(), blackPlayer.getCurrentWeaponsName(),"StartGame",
                    whitePlayer.getPlayer().getCurrentHero().getHealthPower()+"",
                    blackPlayer.getPlayer().getCurrentHero().getHealthPower()+"",
                    whitePlayer.getPlayer().getCurrentHero().getAttackPower()+"",
                    blackPlayer.getPlayer().getCurrentHero().getAttackPower()+"",
                    whitePlayer.giveCurrentWeaponDurability()+"",
                    blackPlayer.giveCurrentWeaponDurability()+"",
                    whitePlayer.giveCurrentWeaponAttackPower()+"",
                    blackPlayer.giveCurrentWeaponAttackPower()+"");
        }else {
            ArrayList<String> whiteHandsCards = GamePartController.giveNameOfCardsList(whitePlayer.getHandsCards());
            ArrayList<String> whiteBattleGroundCards = GamePartController.giveNameOfCardsList(whitePlayer.getBattleGroundCards());
            ArrayList<String> blackHandsCards = GamePartController.giveNameOfCardsList(blackPlayer.getHandsCards());
            ArrayList<String> blackBattleGroundCards = GamePartController.giveNameOfCardsList(blackPlayer.getBattleGroundCards());
            response = new ShowPlayPanelResponse(getUserName(),whiteHandsCards, blackHandsCards, whiteBattleGroundCards,
                    blackBattleGroundCards, whitePlayer.getHeroesName(), blackPlayer.getHeroesName(),
                    whitePlayer.getCurrentWeaponsName(), blackPlayer.getCurrentWeaponsName(),"StartGame",
                    whitePlayer.getPlayer().getCurrentHero().getHealthPower()+"",
                    blackPlayer.getPlayer().getCurrentHero().getHealthPower()+"",
                    whitePlayer.getPlayer().getCurrentHero().getAttackPower()+"",
                    blackPlayer.getPlayer().getCurrentHero().getAttackPower()+"",
                    whitePlayer.giveCurrentWeaponDurability()+"",
                    blackPlayer.giveCurrentWeaponDurability()+"",
                    whitePlayer.giveCurrentWeaponAttackPower()+"",
                    blackPlayer.giveCurrentWeaponAttackPower()+"");
        }
        Server.getDataBaseHandler().save(player);
        return response;
    }

}
