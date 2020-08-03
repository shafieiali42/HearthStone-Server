package controller.controllers;

import Logic.PlayLogic.Game;
import Models.Player.InGamePlayer;
import Models.Player.Player;
import Visitors.PassiveVisitor.InfoPassiveVisitor;

import java.util.ArrayList;

public class GamePartController {


    public static void setFriendlyInfoPassiveOfGameState(InGamePlayer player, int numberOfPassive) {
        player.setInfoPassive(player.getPassivesToChoose().get(numberOfPassive));
        player.getInfoPassive().accept(new InfoPassiveVisitor(), player,
                player.getBattleGroundCards(),
                player.getHandsCards(),
                player.getDeckCards());

    }


    public static ArrayList<String> setNameOfFirstFriendlyThreeCards(InGamePlayer player) {
        ArrayList<String> firstThreeCardsName = new ArrayList<>();
        firstThreeCardsName.add(player.getFirstThreeCards().get(0).getName());
        firstThreeCardsName.add(player.getFirstThreeCards().get(1).getName());
        firstThreeCardsName.add(player.getFirstThreeCards().get(2).getName());
        return firstThreeCardsName;
    }


}
