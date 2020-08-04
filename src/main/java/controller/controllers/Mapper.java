package controller.controllers;

import Logic.PlayLogic.Alliance;
import Logic.PlayLogic.Game;
import Models.Cards.CardClasses.Cards;
import Models.Cards.CardClasses.Minion;
import Models.Cards.CardClasses.Spell;
import Models.Cards.CardClasses.Weapon;
import Models.Player.InGamePlayer;
import Visitors.CardVisitors.*;
import Visitors.PassiveVisitor.PassiveEndTurnVisitor;
import Visitors.PowerVisitor.HeroPowerVisitor.HeroPowerVisitor;
import Visitors.PowerVisitor.HeroPowerVisitor.SummonVisitorOfHeroPowers;
import Visitors.PowerVisitor.HeroPowerVisitor.TargetVisitorOfPowers;
import controller.Status;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Mapper {

    private boolean AddedBeforeForBeingBetween = false;

    public boolean isAddedBeforeForBeingBetween() {
        return AddedBeforeForBeingBetween;
    }

    public void setAddedBeforeForBeingBetween(boolean addedBeforeForBeingBetween) {
        AddedBeforeForBeingBetween = addedBeforeForBeingBetween;
    }


    private ArrayList<Request> requests;
    private Cards cards;

    public Cards getCards() {
        return cards;
    }

    public void setCards(Cards cards) {
        this.cards = cards;
    }

    private Mapper() {
        requests = new ArrayList<Request>();
    }

    public void addRequest(RequestTypes requestType) {
        if (requestType != null)
            requests.add(requestType);
    }

    public void executeRequests() {
        for (Iterator<Request> requestIterator = requests.iterator(); requestIterator.hasNext(); ) {
            Request request = requestIterator.next();
            request.execute();
            requestIterator.remove();
            PlayPanel.getInstance().setNeedsToRepaint(true);
        }
    }

    public enum RequestTypes implements Request {

        PLAY_CARDS {
            @Override
            public void execute() {
                View.Gui.Mapper.getInstance().playCard(Game.getInstance().getPlayingCard(), 7);
            }
        },
        PLAY_CARDS_ONE {
            @Override
            public void execute() {
                View.Gui.Mapper.getInstance().playCard(Game.getInstance().getPlayingCard(), 1);
            }
        },
        PLAY_CARDS_TWO {
            @Override
            public void execute() {
                View.Gui.Mapper.getInstance().playCard(Game.getInstance().getPlayingCard(), 2);
            }
        },
        PLAY_CARDS_THREE {
            @Override
            public void execute() {
                View.Gui.Mapper.getInstance().playCard(Game.getInstance().getPlayingCard(), 3);
            }
        },
        PLAY_CARDS_FOUR {
            @Override
            public void execute() {
                View.Gui.Mapper.getInstance().playCard(Game.getInstance().getPlayingCard(), 4);
            }
        },
        PLAY_CARDS_FIVE {
            @Override
            public void execute() {
                View.Gui.Mapper.getInstance().playCard(Game.getInstance().getPlayingCard(), 5);
            }
        },
        PLAY_CARDS_SIX {
            @Override
            public void execute() {
                View.Gui.Mapper.getInstance().playCard(Game.getInstance().getPlayingCard(), 6);
            }
        },
        PLAY_HERO_POWER {
            @Override
            public void execute() {
                View.Gui.Mapper.getInstance().playHeroPower();
            }
        },
        END_TURN {
            @Override
            public void execute() {
                endTurn();
            }
        };


    }


    public static void writeOnLogPanel(String log) {
        StringBuilder addToLog = new StringBuilder(LogPanel.getInstance().getLog());
        for (int i = 0; i < log.length(); i++) {
            char c = log.charAt(i);
            if (Character.isUpperCase(c)) {
                addToLog.append("\n");
                addToLog.append(c);
            } else {
                addToLog.append(c);
            }
        }
        LogPanel.getInstance().setLog(addToLog.toString() + "\n\n");
        LogPanel.getInstance().repaint();
        LogPanel.getInstance().revalidate();
    }


    public void playHeroPower() {
        if (Game.getInstance().getCurrentPlayer().getMana() < Game.getInstance().getCurrentPlayer().getHero().getHeroPower().getMana()) {
            JOptionPane.showMessageDialog(null, "You don't have enough mana");
            return;
        }
        Game.getInstance().getCurrentPlayer().setMana(Game.getInstance().getCurrentPlayer().getMana() -
                Game.getInstance().getCurrentPlayer().getHero().getHeroPower().getMana());

        //for heroPowers witch doesnt need target

        Game.getInstance().getCurrentPlayer().getHero().getHeroPower().accept(new HeroPowerVisitor(),
                Game.getInstance().getCurrentPlayer(),
                Game.getInstance().getCurrentPlayer().getBattleGroundCards(),
                Game.getInstance().getFormerPlayer().getBattleGroundCards(),
                Game.getInstance().getCurrentPlayer().getHandsCards(),
                Game.getInstance().getFormerPlayer().getHandsCards(),
                Game.getInstance().getCurrentPlayer().getDeckCards(),
                Game.getInstance().getFormerPlayer().getDeckCards(),
                new Minion(), null, null);//todo json

        //for heroPowers witch need target
        Game.getInstance().getCurrentPlayer().getHero().getHeroPower().accept(new TargetVisitorOfPowers(),
                Game.getInstance().getCurrentPlayer(),
                Game.getInstance().getCurrentPlayer().getBattleGroundCards(),
                Game.getInstance().getFormerPlayer().getBattleGroundCards(),
                Game.getInstance().getCurrentPlayer().getHandsCards(),
                Game.getInstance().getFormerPlayer().getHandsCards(),
                Game.getInstance().getCurrentPlayer().getDeckCards(),
                Game.getInstance().getFormerPlayer().getDeckCards(),
                new Minion(), null, null);//todo json


    }


    public static void playCard(Cards playingCard) {
//        System.out.println(playingCard.getType());
        Game.getInstance().getCurrentPlayer().setMana(Game.getInstance().getCurrentPlayer().getMana() - playingCard.getManaCost());
        if (playingCard.getType().contains("Quest")) {
            Game.getInstance().getCurrentPlayer().setQuestCard((Spell) playingCard);
        } else {
            if (Game.getInstance().getCurrentPlayer().getQuestCard() != null) {
                Game.getInstance().getCurrentPlayer().getQuestCard().accept(new QuestRewardVisitor(),
                        Game.getInstance().getCurrentPlayer().getBattleGroundCards(),
                        Game.getInstance().getCurrentPlayer().getHandsCards(),
                        Game.getInstance().getCurrentPlayer().getDeckCards(), null,
                        null, null, playingCard, null);
            }
        }

        if (!playingCard.getType().contains("Spell")) {
            PlayPanel.getInstance().setNeedAnimation(true);
        }
        PlayPanel.getInstance().repaint();
        PlayPanel.getInstance().revalidate();
        Game.getInstance().getCurrentPlayer().getHandsCards().remove(playingCard);

        writeOnLogPanel("Play "+playingCard.getName());
        Sounds.playActionSounds("src/main/resources/Sounds/ActionVoices/PlayCards.wav");
        ControllerOfMainComponents.currentPlayer.getLoggerOfMyPlayer().info("Play" + playingCard.getType());
    }

    public void playCard(Cards playingCard, int k) {
        if (playingCard.getManaCost() > Game.getInstance().getCurrentPlayer().getMana()) {
            JOptionPane.showMessageDialog(null, "You don't have enough mana");
        } else if (playingCard.getManaCost() <= Game.getInstance().getCurrentPlayer().getMana()) {
            if (playingCard.getType().equalsIgnoreCase("minion")) {
                playMinion((Minion) playingCard, k);
            } else if (playingCard.getType().equalsIgnoreCase("weapon")) {
                playWeapon(playingCard);
            } else if (playingCard.getType().contains("Spell")) {
                playSpell(playingCard);
            }
        }
    }


    public static Cards drawOneCard() {
        if (Game.getInstance().getCurrentPlayer().getHandsCards().size() < 12) {
            if (Game.getInstance().getCurrentPlayer().getDeckCards().size() == 0) {
                JOptionPane.showMessageDialog(null,
                        "Your deck is empty.\nContinue game with your hand's cards", "Error", JOptionPane.ERROR_MESSAGE);

                return null;

            } else {
                Cards card = Game.getInstance().getCurrentPlayer().getDeckCards().get(0);
                Game.getInstance().getCurrentPlayer().getDeckCards().remove(0);
                return card;

            }
        } else {
            Game.getInstance().getCurrentPlayer().getDeckCards().remove(0);
            JOptionPane.showMessageDialog(null,
                    "You can't have more than 12 cards in your hand", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

//    public static void drawCard() {
////        System.out.println(Game.getInstance().getEnemyPlayer().getHandsCards());
//        Game.getInstance().getFormerPlayer().getInfoPassive().accept(new PassiveEndTurnVisitor(),
//                Game.getInstance().getCurrentPlayer(), Game.getInstance().getCurrentPlayer().getBattleGroundCards(),
//                Game.getInstance().getCurrentPlayer().getHandsCards(), Game.getInstance().getCurrentPlayer().getDeckCards());
//        if (Game.getInstance().getCurrentPlayer().getHandsCards().size() < 12) {
//            if (Game.getInstance().getCurrentPlayer().getDeckCards().size() == 0) {
//                JOptionPane.showMessageDialog(null,
//                        "Your deck is empty.\nContinue game with your hand's cards", "Error", JOptionPane.ERROR_MESSAGE);
//            } else {
//                Game.getInstance().getCurrentPlayer().getHandsCards().add(Game.getInstance().getCurrentPlayer().getDeckCards().get(0));
//
//                Game.getInstance().getCurrentPlayer().getDeckCards().remove(0);
//
//            }
//        } else {
//            Game.getInstance().getCurrentPlayer().getDeckCards().remove(0);
//            JOptionPane.showMessageDialog(null,
//                    "You can't have more than 12 cards in your hand", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//
//        for (Minion minion : Game.getInstance().getCurrentPlayer().getBattleGroundCards()) {
//            minion.accept(new DrawCardVisitor(), Game.getInstance().getCurrentPlayer().getBattleGroundCards(),
//                    Game.getInstance().getCurrentPlayer().getHandsCards(), new ArrayList<Cards>(), minion, null, new Minion(),
//                    null, null);
//        }
//
//
//        if (Game.getInstance().getCurrentPlayer().getNumberOfDrawCard() > 1) {
//            for (int i = 0; i < Game.getInstance().getCurrentPlayer().getNumberOfDrawCard() - 1; i++) {
//                if (Game.getInstance().getCurrentPlayer().getHandsCards().size() < 12) {
//                    if (Game.getInstance().getCurrentPlayer().getDeckCards().size() != 0) {
//                        Game.getInstance().getCurrentPlayer().getHandsCards().add(Game.getInstance().getCurrentPlayer().getDeckCards().get(0));
//                        Game.getInstance().getCurrentPlayer().getDeckCards().remove(0);
//                    }
//                }
//            }
//        }
//
//    }
//
//    public static void nextTurn() {
//        Game.getInstance().getMyTimer().reStart();
//        Game.getInstance().getCurrentPlayer().setTurn(Game.getInstance().getCurrentPlayer().getTurn() + 1);
//        Game.getInstance().getCurrentPlayer().setMana((int) Math.min(Game.getInstance().getCurrentPlayer().getTurn(), 10));
//        if (Game.getInstance().getCurrentAlliance().equals(Alliance.FRIENDLY)) {
//            Game.getInstance().setCurrentPlayer(Game.getInstance().getEnemyPlayer());
//            Game.getInstance().setFormerPlayer(Game.getInstance().getFriendlyPlayer());
//            Game.getInstance().setCurrentAlliance(Alliance.ENEMY);
//        } else if (Game.getInstance().getCurrentAlliance().equals(Alliance.ENEMY)) {
//            Game.getInstance().setCurrentPlayer(Game.getInstance().getFriendlyPlayer());
//            Game.getInstance().setFormerPlayer(Game.getInstance().getEnemyPlayer());
//            Game.getInstance().setCurrentAlliance(Alliance.FRIENDLY);
//        }
//
//    }


//    public static void setCanBeAttacked(InGamePlayer player) {
//        boolean hasTaunt = false;
//        for (Minion minion : player.getBattleGroundCards()) {
//            if (minion.isTaunt()) {
//                hasTaunt = true;
//                break;
//            }
//        }
//        if (hasTaunt) {
//            player.getHero().setCanBeAttacked(false);
//            for (Minion minion : player.getBattleGroundCards()) {
//                if (!minion.isTaunt()) {
//                    minion.setCanBeAttacked(false);
//                }
//            }
//        }
//    }
//
//    public static void setIsActives(InGamePlayer player) {
//        for (Minion minion : player.getBattleGroundCards()) {
//            minion.setHasAttackInThisTurn(false);
//            minion.setIsActive(true);
//        }
//    }
//
//    public static void endTurn(InGamePlayer player) {
//        setCanBeAttacked(player);
//        setIsActives(player);
//        player.getPlayer().setPlayerStatusInGame(Status.PLAY_PAGE);
//        GamePartController.setNeedTimer(false);
////        System.out.println(Game.getInstance().getCurrentPlayer().getBattleGroundCards());
//        Iterator<Minion> itr = player.getBattleGroundCards().iterator();
//
//        while (itr.hasNext()) {
//            Minion minion = itr.next();
//            minion.accept(new EndTurnVisitor(), player.getBattleGroundCards(),
//                    player.getHandsCards(), new ArrayList<Cards>(), minion, null,
//                    new Minion(), null, null);//todo json
//
//        }
//
//        nextTurn();
//        drawCard();
//        Sounds.playActionSounds("src/main/resources/Sounds/ActionVoices/EndTurn.wav");
//        ControllerOfMainComponents.currentPlayer.getLoggerOfMyPlayer().info("End turn");
//    }

    public static void playMinion(Minion playingCard, int k) {
//        System.out.println(playingCard);
        boolean minionPlayed = false;
        if (k != 7) {
            if (Game.getInstance().getCurrentPlayer().getBattleGroundCards().size() >= k) {
//                ArrayList<Minion> copy = new ArrayList<>(Game.getInstance().getCurrentPlayer().getBattleGroundCards());
                ArrayList<Minion> copy = (ArrayList<Minion>) Game.getInstance().getCurrentPlayer().getBattleGroundCards().clone();
                Game.getInstance().getCurrentPlayer().getBattleGroundCards().clear();
                boolean isAdded = false;
                for (int j = 0; j < copy.size(); j++) {
                    if (j == (k - 1) && !isAdded) {
                        System.out.println(playingCard);
                        System.out.println(playingCard.copy());
                        Game.getInstance().getCurrentPlayer().getBattleGroundCards().add(playingCard.copy());
                        isAdded = true;
                        j--;
                    } else {
                        Game.getInstance().getCurrentPlayer().getBattleGroundCards().add(copy.get(j).copy());
                    }
                }
                View.Gui.Mapper.getInstance().setAddedBeforeForBeingBetween(true);
                minionPlayed = true;
            }
        } else {
            Game.getInstance().getCurrentPlayer().getBattleGroundCards().add(playingCard.copy());
            View.Gui.Mapper.getInstance().setAddedBeforeForBeingBetween(false);
            minionPlayed = true;

            Game.getInstance().getFormerPlayer().getHero().getHeroPower().accept(new SummonVisitorOfHeroPowers(),
                    null, null, null,
                    null, null, null,
                    null, null, null,
                    Game.getInstance().getCurrentPlayer().getBattleGroundCards().
                            get(Game.getInstance().getCurrentPlayer().getBattleGroundCards().size() - 1));


            for (Cards card : Game.getInstance().getFormerPlayer().getBattleGroundCards()) {
                card.accept(new EnemySummonVisitor(), null, null, null,
                        null, null,
                        Game.getInstance().getCurrentPlayer().getBattleGroundCards().
                                get(Game.getInstance().getCurrentPlayer().getBattleGroundCards().size() - 1),
                        null, null);
            }


            if (Game.getInstance().getCurrentPlayer().getBattleGroundCards().size() > (k - 1)) {
                for (Minion minion : Game.getInstance().getCurrentPlayer().getBattleGroundCards()) {
                    minion.accept(new SummonVisitor(), Game.getInstance().getCurrentPlayer().getBattleGroundCards(),
                            Game.getInstance().getCurrentPlayer().getHandsCards(), new ArrayList<Cards>(), new Minion(),
                            null, Game.getInstance().getCurrentPlayer().getBattleGroundCards().get(k - 1),
                            null, null);//todo json
                }
            }

        }
        if (minionPlayed) {
            playCard(playingCard);
            playingCard.accept(new BattleCryVisitor(), Game.getInstance().getCurrentPlayer().getBattleGroundCards(),
                    Game.getInstance().getCurrentPlayer().getHandsCards(), new ArrayList<Cards>(), playingCard,
                    null, new Minion(), null, null);//todo json


            if (k != 7) {

                if (Game.getInstance().getCurrentPlayer().getBattleGroundCards().size() > (k - 1)) {
                    for (Minion minion : Game.getInstance().getCurrentPlayer().getBattleGroundCards()) {
                        minion.accept(new SummonVisitor(), Game.getInstance().getCurrentPlayer().getBattleGroundCards(),
                                Game.getInstance().getCurrentPlayer().getHandsCards(),
                                new ArrayList<Cards>(), new Minion(), null,
                                Game.getInstance().getCurrentPlayer().getBattleGroundCards().get(k - 1), null, null);
                        //todo json
                    }

                }

                if (Game.getInstance().getCurrentPlayer().getBattleGroundCards().size() > (k - 1)) {
                    Game.getInstance().getFormerPlayer().getHero().getHeroPower().accept(new SummonVisitorOfHeroPowers(),
                            null, null, null,
                            null, null, null,
                            null, null, null,
                            Game.getInstance().getCurrentPlayer().getBattleGroundCards().get(k - 1));
                }

                if (Game.getInstance().getCurrentPlayer().getBattleGroundCards().size() > (k - 1)) {
                    for (Cards card : Game.getInstance().getFormerPlayer().getBattleGroundCards()) {
                        card.accept(new EnemySummonVisitor(), null, null, null,
                                null, null, Game.getInstance().getCurrentPlayer().getBattleGroundCards().get(k - 1),
                                null, null);
                    }
                }


            }


        }
    }

    public static void playSpell(Cards playingCard) {
        playCard(playingCard);

        playingCard.accept(new BattleCryVisitor(), Game.getInstance().getCurrentPlayer().getBattleGroundCards(),
                Game.getInstance().getCurrentPlayer().getHandsCards(), new ArrayList<Cards>(), new Minion(),
                null, new Minion(), null, null);

        playingCard.accept(new DrawCardVisitor(), Game.getInstance().getCurrentPlayer().getBattleGroundCards(),
                Game.getInstance().getCurrentPlayer().getHandsCards(), new ArrayList<Cards>(), new Minion(),
                null, new Minion(), null, null);

        playingCard.accept(new ActionVisitor(), Game.getInstance().getCurrentPlayer().getBattleGroundCards(),
                Game.getInstance().getCurrentPlayer().getHandsCards(),
                Game.getInstance().getCurrentPlayer().getDeckCards(), new Minion(), null,
                new Minion(), null, null);

        playingCard.accept(new TargetVisitor(), Game.getInstance().getCurrentPlayer().getBattleGroundCards(),
                Game.getInstance().getCurrentPlayer().getHandsCards(), Game.getInstance().getCurrentPlayer().getDeckCards(),
                new Minion(), null, new Minion(), null, null);//todo ...............
        //todo json

    }

    public static void playWeapon(Cards playingCard) {
        Game.getInstance().getCurrentPlayer().setCurrentWeapon((Weapon) playingCard);
        playCard(playingCard);
        playingCard.accept(new BattleCryVisitor(), Game.getInstance().getCurrentPlayer().getBattleGroundCards(),
                Game.getInstance().getCurrentPlayer().getHandsCards(), new ArrayList<Cards>(), new Minion(),
                null, new Minion(), null, null);


    }


}
