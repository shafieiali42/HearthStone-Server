package controller.controllers;

import Logic.PlayLogic.Game;
import Models.Cards.CardClasses.Cards;
import Models.Cards.CardClasses.Minion;
import Models.Player.InGamePlayer;
import Visitors.CardVisitors.DrawCardVisitor;
import Visitors.CardVisitors.EndTurnVisitor;
import Visitors.PassiveVisitor.InfoPassiveVisitor;
import Visitors.PassiveVisitor.PassiveEndTurnVisitor;
import controller.Status;
import server.Server;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GamePartController {


    public static void setFriendlyInfoPassiveOfGameState(InGamePlayer player, int numberOfPassive) {
        player.setInfoPassive(player.getPassivesToChoose().get(numberOfPassive));
        player.getInfoPassive().accept(new InfoPassiveVisitor(), player,
                player.getBattleGroundCards(),
                player.getHandsCards(),
                player.getDeckCards());

    }


    public static ArrayList<String> giveNameOfCardsList(ArrayList<? extends Cards> cards) {
        ArrayList<String> handsCardNames = new ArrayList<>();
        for (Cards card : cards) {
            handsCardNames.add(card.getName());
        }
        return handsCardNames;
    }


    public static int ChangeThisCardFromHands(String cardName, String firstCard, String secondCard,
                                              String thirdCard, boolean firstBoolean, boolean secondBoolean,
                                              boolean thirdBoolean, InGamePlayer player) {
        boolean changed = false;
        int cardChanged = -1;
        if (cardName.equals(firstCard) && firstBoolean) {
            changed = true;
            cardChanged = 1;
//            FirstThreeCardsPage.getInstance().setCanChangeFirstCard(false);
            player.getDeckCards().add(player.getFirstThreeCards().get(0));
            player.getFirstThreeCards().remove(0);
            player.getFirstThreeCards().add(0, player.getDeckCards().get(3));
            player.getDeckCards().remove(3);
            player.setHandsCards(player.getFirstThreeCards());
        } else if (cardName.equals(secondCard) && secondBoolean) {
            changed = true;
            cardChanged = 2;
//            FirstThreeCardsPage.getInstance().setCanChangeSecondCard(false);
            player.getDeckCards().add(player.getFirstThreeCards().get(1));
            player.getFirstThreeCards().remove(1);
            player.getFirstThreeCards().add(1, player.getDeckCards().get(3));
            player.getDeckCards().remove(3);
            player.setHandsCards(player.getFirstThreeCards());
        } else if (cardName.equals(thirdCard) && thirdBoolean) {
            changed = true;
            cardChanged = 3;
//            FirstThreeCardsPage.getInstance().setCanChangeThirdCard(false);
            player.getDeckCards().add(player.getFirstThreeCards().get(2));
            player.getFirstThreeCards().remove(2);
            player.getFirstThreeCards().add(2, player.getDeckCards().get(3));
            player.getDeckCards().remove(3);
            player.setHandsCards(player.getFirstThreeCards());
        }
        return cardChanged;
    }


    public static ArrayList<String> setNameOfFirstFriendlyThreeCards(InGamePlayer player) {
        ArrayList<String> firstThreeCardsName = new ArrayList<>();
        firstThreeCardsName.add(player.getFirstThreeCards().get(0).getName());
        firstThreeCardsName.add(player.getFirstThreeCards().get(1).getName());
        firstThreeCardsName.add(player.getFirstThreeCards().get(2).getName());
        return firstThreeCardsName;
    }


    public static void setCanBeAttacked(InGamePlayer player) {
        boolean hasTaunt = false;
        for (Minion minion : player.getBattleGroundCards()) {
            if (minion.isTaunt()) {
                hasTaunt = true;
                break;
            }
        }
        if (hasTaunt) {
            player.getHero().setCanBeAttacked(false);
            for (Minion minion : player.getBattleGroundCards()) {
                if (!minion.isTaunt()) {
                    minion.setCanBeAttacked(false);
                }
            }
        }
    }

    public static void setIsActives(InGamePlayer player) {
        for (Minion minion : player.getBattleGroundCards()) {
            minion.setHasAttackInThisTurn(false);
            minion.setIsActive(true);
        }
    }


    public static String drawCard(InGamePlayer player) {
        String message = "Successful";
        Game game = Server.giveGameWithPlayer(player.getPlayer().getUserName());
        game.getFormerPlayer().getInfoPassive().accept(new PassiveEndTurnVisitor(),
                game.getCurrentPlayer(), game.getCurrentPlayer().getBattleGroundCards(),
                game.getCurrentPlayer().getHandsCards(), game.getCurrentPlayer().getDeckCards());
        if (game.getCurrentPlayer().getHandsCards().size() < 12) {
            if (game.getCurrentPlayer().getDeckCards().size() == 0) {
                message = "Your deck is empty.\nContinue game with your hand's cards";
            } else {
                game.getCurrentPlayer().getHandsCards().add(game.getCurrentPlayer().getDeckCards().get(0));

                game.getCurrentPlayer().getDeckCards().remove(0);

            }
        } else {
            game.getCurrentPlayer().getDeckCards().remove(0);
            message = "You can't have more than 12 cards in your hand";
        }

        for (Minion minion : game.getCurrentPlayer().getBattleGroundCards()) {
            minion.accept(new DrawCardVisitor(), game.getCurrentPlayer().getBattleGroundCards(),
                    game.getCurrentPlayer().getHandsCards(), new ArrayList<Cards>(), minion, null, new Minion(),
                    null, null);
        }


        if (game.getCurrentPlayer().getNumberOfDrawCard() > 1) {
            for (int i = 0; i < game.getCurrentPlayer().getNumberOfDrawCard() - 1; i++) {
                if (game.getCurrentPlayer().getHandsCards().size() < 12) {
                    if (game.getCurrentPlayer().getDeckCards().size() != 0) {
                        game.getCurrentPlayer().getHandsCards().add(game.getCurrentPlayer().getDeckCards().get(0));
                        game.getCurrentPlayer().getDeckCards().remove(0);
                    }
                }
            }
        }
        return message;
    }

    public static void nextTurn(InGamePlayer player) {
        Game game = Server.giveGameWithPlayer(player.getPlayer().getUserName());
        game.getMyTimer().reStart();
        game.getCurrentPlayer().setTurn(game.getCurrentPlayer().getTurn() + 1);
        game.getCurrentPlayer().setMana((int) Math.min(game.getCurrentPlayer().getTurn(), 10));
        game.changeTurn();
    }


    public static String endTurn(InGamePlayer player) {
        String message = "Successful";
        setCanBeAttacked(player);
        setIsActives(player);
        player.getPlayer().setPlayerStatusInGame(Status.PLAY_PAGE);
//        System.out.println(Game.getInstance().getCurrentPlayer().getBattleGroundCards());
        Iterator<Minion> itr = player.getBattleGroundCards().iterator();

        while (itr.hasNext()) {
            Minion minion = itr.next();
            minion.accept(new EndTurnVisitor(), player.getBattleGroundCards(),
                    player.getHandsCards(), new ArrayList<Cards>(), minion, null,
                    new Minion(), null, null);//todo json

        }
        nextTurn(player);
        message = drawCard(player);
//        ControllerOfMainComponents.currentPlayer.getLoggerOfMyPlayer().info("End turn");
        return message;
    }


}
