package controller.request;

import Logic.PlayLogic.Game;
import Models.Player.InGamePlayer;
import Models.Player.Player;
import controller.Status;
import controller.controllers.GamePartController;
import controller.response.Response;
import controller.response.ShowPlayPanelResponse;
import server.Server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OkButtonOnFirstThreeCardsPageRequest extends Request {


    public OkButtonOnFirstThreeCardsPageRequest(String userName) {

        setUserName(userName);
    }

    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(getUserName());
        Game game=Server.giveGameWithPlayer(getUserName());
        Response response = null;
        player.setPlayerStatusInGame(Status.PLAY_PAGE);
        InGamePlayer whitePlayer = Server.giveInGamePlayer(getUserName());
        InGamePlayer blackPlayer = Server.giveAnotherPlayer(getUserName());

        HashMap<String,String> whitePlayerHp=
                GamePartController.giveMapOfHpAndAttack(whitePlayer.getBattleGroundCards(),"HP");
        HashMap<String,String> whitePlayerAttack=
                GamePartController.giveMapOfHpAndAttack(whitePlayer.getBattleGroundCards(),"Attack");
        HashMap<String,String> blackPlayerHp=
                GamePartController.giveMapOfHpAndAttack(blackPlayer.getBattleGroundCards(),"HP");
        HashMap<String,String> blackPlayerAttack=
                GamePartController.giveMapOfHpAndAttack(blackPlayer.getBattleGroundCards(),"Attack");
        if (!Server.giveGameWithPlayer(getUserName()).getGameMode().equalsIgnoreCase("OfflineGame")) {
            ArrayList<String> whiteHandsCards = GamePartController.giveNameOfCardsList(whitePlayer.getHandsCards());
            ArrayList<String> whiteBattleGroundCards = GamePartController.giveNameOfCardsList(whitePlayer.getBattleGroundCards());
            ArrayList<String> blackBattleGroundCards = GamePartController.giveNameOfCardsList(blackPlayer.getBattleGroundCards());
            ArrayList<String> blackHandsCards = GamePartController.giveNameOfCardsList(blackPlayer.getHandsCards());
            ArrayList<String> fakeBlackHandsCards =new ArrayList<>();
            for (int i = 0; i < blackPlayer.getHandsCards().size(); i++) {
                fakeBlackHandsCards.add(i+"");
            }
            ArrayList<String> fakeWhiteHandsCards =new ArrayList<>();
            for (int i = 0; i < whitePlayer.getHandsCards().size(); i++) {
                fakeWhiteHandsCards.add(i+"");
            }


            response = new ShowPlayPanelResponse(getUserName(), whiteHandsCards,fakeBlackHandsCards, whiteBattleGroundCards,
                   blackBattleGroundCards, whitePlayer.getHeroesName(), blackPlayer.getHeroesName(),
                    whitePlayer.getCurrentWeaponsName(), blackPlayer.getCurrentWeaponsName(), "StartGame",
                    whitePlayer.getPlayer().getCurrentHero().getHealthPower() + "",
                    blackPlayer.getPlayer().getCurrentHero().getHealthPower() + "",
                    whitePlayer.getPlayer().getCurrentHero().getAttackPower() + "",
                    blackPlayer.getPlayer().getCurrentHero().getAttackPower() + "",
                    whitePlayer.giveCurrentWeaponDurability() + "",
                    blackPlayer.giveCurrentWeaponDurability() + "",
                    whitePlayer.giveCurrentWeaponAttackPower() + "",
                    blackPlayer.giveCurrentWeaponAttackPower() + "",
                    game.getCurrentPlayer().getMana() + "",
                    whitePlayerHp,whitePlayerAttack,blackPlayerHp,blackPlayerAttack);

            Response responseForAnotherPlayer=
                    new ShowPlayPanelResponse(blackPlayer.getPlayer().getUserName(),
                            blackHandsCards,fakeWhiteHandsCards, blackBattleGroundCards,
                    whiteBattleGroundCards, blackPlayer.getHeroesName(), whitePlayer.getHeroesName(),
                    blackPlayer.getCurrentWeaponsName(), whitePlayer.getCurrentWeaponsName(), "StartGame",
                    blackPlayer.getPlayer().getCurrentHero().getHealthPower() + "",
                    whitePlayer.getPlayer().getCurrentHero().getHealthPower() + "",
                    blackPlayer.getPlayer().getCurrentHero().getAttackPower() + "",
                    whitePlayer.getPlayer().getCurrentHero().getAttackPower() + "",
                    blackPlayer.giveCurrentWeaponDurability() + "",
                    whitePlayer.giveCurrentWeaponDurability() + "",
                    blackPlayer.giveCurrentWeaponAttackPower() + "",
                    whitePlayer.giveCurrentWeaponAttackPower() + "",
                    game.getCurrentPlayer().getMana() + "",
                            blackPlayerHp,blackPlayerAttack,whitePlayerHp,whitePlayerAttack);
            Server.sendResponse(blackPlayer.getPlayer().getUserName(),responseForAnotherPlayer);
        } else {
            ArrayList<String> whiteHandsCards = GamePartController.giveNameOfCardsList(whitePlayer.getHandsCards());
            ArrayList<String> whiteBattleGroundCards = GamePartController.giveNameOfCardsList(whitePlayer.getBattleGroundCards());
            ArrayList<String> blackHandsCards = GamePartController.giveNameOfCardsList(blackPlayer.getHandsCards());
            ArrayList<String> blackBattleGroundCards = GamePartController.giveNameOfCardsList(blackPlayer.getBattleGroundCards());
            response = new ShowPlayPanelResponse(getUserName(), whiteHandsCards, blackHandsCards, whiteBattleGroundCards,
                    blackBattleGroundCards, whitePlayer.getHeroesName(), blackPlayer.getHeroesName(),
                    whitePlayer.getCurrentWeaponsName(), blackPlayer.getCurrentWeaponsName(), "StartGame",
                    whitePlayer.getPlayer().getCurrentHero().getHealthPower() + "",
                    blackPlayer.getPlayer().getCurrentHero().getHealthPower() + "",
                    whitePlayer.getPlayer().getCurrentHero().getAttackPower() + "",
                    blackPlayer.getPlayer().getCurrentHero().getAttackPower() + "",
                    whitePlayer.giveCurrentWeaponDurability() + "",
                    blackPlayer.giveCurrentWeaponDurability() + "",
                    whitePlayer.giveCurrentWeaponAttackPower() + "",
                    blackPlayer.giveCurrentWeaponAttackPower() + "",
                    game.getCurrentPlayer().getMana() + "",
                    whitePlayerHp,whitePlayerAttack,blackPlayerHp,blackPlayerAttack);

            Response responseForAnotherPlayer=
                    new ShowPlayPanelResponse(blackPlayer.getPlayer().getUserName(),
                            blackHandsCards,whiteHandsCards, blackBattleGroundCards,
                            whiteBattleGroundCards, blackPlayer.getHeroesName(), whitePlayer.getHeroesName(),
                            blackPlayer.getCurrentWeaponsName(), whitePlayer.getCurrentWeaponsName(), "StartGame",
                            blackPlayer.getPlayer().getCurrentHero().getHealthPower() + "",
                            whitePlayer.getPlayer().getCurrentHero().getHealthPower() + "",
                            blackPlayer.getPlayer().getCurrentHero().getAttackPower() + "",
                            whitePlayer.getPlayer().getCurrentHero().getAttackPower() + "",
                            blackPlayer.giveCurrentWeaponDurability() + "",
                            whitePlayer.giveCurrentWeaponDurability() + "",
                            blackPlayer.giveCurrentWeaponAttackPower() + "",
                            whitePlayer.giveCurrentWeaponAttackPower() + "",
                            game.getCurrentPlayer().getMana() + "",
                            blackPlayerHp,blackPlayerAttack,whitePlayerHp,whitePlayerAttack);
            Server.sendResponse(blackPlayer.getPlayer().getUserName(),responseForAnotherPlayer);
        }
        Server.getDataBaseHandler().save(player);
        return response;
    }

}
