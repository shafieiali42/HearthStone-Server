package controller.controllers;

import Logic.PlayLogic.Alliance;
import Logic.PlayLogic.Game;
import Models.Cards.CardClasses.Cards;
import Models.Cards.CardClasses.Minion;
import Models.Cards.CardClasses.Spell;
import Models.Cards.CardClasses.Weapon;
import Models.HeroPower.HeroPower;
import Models.Heroes.Heroes;
import Models.Player.InGamePlayer;
import Visitors.CardVisitors.*;
import Visitors.PassiveVisitor.InfoPassiveVisitor;
import Visitors.PassiveVisitor.PassiveEndTurnVisitor;
import Visitors.PowerVisitor.HeroPowerVisitor.HeroPowerVisitor;
import Visitors.PowerVisitor.HeroPowerVisitor.SummonVisitorOfHeroPowers;
import Visitors.PowerVisitor.HeroPowerVisitor.TargetVisitorOfPowers;
import controller.Status;
import server.Server;

import javax.swing.*;
import java.util.*;

public class GamePartController {


//    public static void writeOnLogPanel(String log) {
//        StringBuilder addToLog = new StringBuilder(LogPanel.getInstance().getLog());
//        for (int i = 0; i < log.length(); i++) {
//            char c = log.charAt(i);
//            if (Character.isUpperCase(c)) {
//                addToLog.append("\n");
//                addToLog.append(c);
//            } else {
//                addToLog.append(c);
//            }
//        }
//        LogPanel.getInstance().setLog(addToLog.toString() + "\n\n");
//        LogPanel.getInstance().repaint();
//        LogPanel.getInstance().revalidate();
//    }




    public static HashMap<String,String> giveMapOfHpAndAttack(ArrayList<Minion> minions,String hpOrAttack){
       HashMap<String,String> map=new HashMap<>();
        for (int i=0;i<minions.size();i++){
            if(hpOrAttack.equalsIgnoreCase("Hp")){
                map.put((i+1)+"",minions.get(i).getHealthPower()+"");
            }else if (hpOrAttack.equalsIgnoreCase("Attack")){
                map.put((i+1)+"",minions.get(i).getAttackPower()+"");
            }
        }
        return map;
    }



    public static ArrayList<String> setThreeWeapon() {

        ArrayList<String> weaponsNames = new ArrayList<>();
        Random random = new Random();
        int firstNum = random.nextInt(Weapon.getWeapons().size());
        int secondNum = random.nextInt(Weapon.getWeapons().size());
        int thirdNum = random.nextInt(Weapon.getWeapons().size());

        weaponsNames.add(Weapon.getWeapons().get(firstNum).getName());
        weaponsNames.add(Weapon.getWeapons().get(secondNum).getName());
        weaponsNames.add(Weapon.getWeapons().get(thirdNum).getName());

        return weaponsNames;
    }


    public static Weapon getSelectedWeapon(Game game) {
        for (Weapon weapon : Weapon.getWeapons()) {
            if (weapon.getName().equalsIgnoreCase(game.getSelectedWeaponInDiscoverPage())) {
                return weapon;
            }
        }
        return null;
    }


    public static String playCard(Cards playingCard, Game game) {
//        System.out.println(playingCard.getType());
        String result = null;
        game.getCurrentPlayer().setMana(game.getCurrentPlayer().getMana() - playingCard.getManaCost());
        if (playingCard.getType().contains("Quest")) {
            game.getCurrentPlayer().setQuestCard((Spell) playingCard);
        } else {
            if (game.getCurrentPlayer().getQuestCard() != null) {
                game.getCurrentPlayer().getQuestCard().accept(new QuestRewardVisitor(),
                        game.getCurrentPlayer().getBattleGroundCards(),
                        game.getCurrentPlayer().getHandsCards(),
                        game.getCurrentPlayer().getDeckCards(), null,
                        null, null, playingCard, null, game);
            }
        }

        if (!playingCard.getType().contains("Spell")) {
            result = "NonSpell";
//            PlayPanel.getInstance().setNeedAnimation(true);
        } else {
            result = "Spell";
        }
//        PlayPanel.getInstance().repaint();
//        PlayPanel.getInstance().revalidate();
        game.getCurrentPlayer().getHandsCards().remove(playingCard);

//        writeOnLogPanel("Play " + playingCard.getName());
//        Sounds.playActionSounds("src/main/resources/Sounds/ActionVoices/PlayCards.wav");
//        ControllerOfMainComponents.currentPlayer.getLoggerOfMyPlayer().info("Play" + playingCard.getType());
        return result;
    }

    public static String playCard(Game game, int k) {
        String result = "Successful";
        Cards playingCard = game.getPlayingCard();
        if (playingCard.getManaCost() > game.getCurrentPlayer().getMana()) {
            result = "mana";
//            JOptionPane.showMessageDialog(null, "You don't have enough mana");
        } else if (playingCard.getManaCost() <= game.getCurrentPlayer().getMana()) {
            if (playingCard.getType().equalsIgnoreCase("minion")) {
                result = playMinion((Minion) playingCard, game, k);
            } else if (playingCard.getType().equalsIgnoreCase("weapon")) {
                result = playWeapon(playingCard, game);
            } else if (playingCard.getType().contains("Spell")) {
                result = playSpell(playingCard, game);
            }
        }

        return result;
    }


    public static Cards drawOneCard(Game game) {
        if (game.getCurrentPlayer().getHandsCards().size() < 12) {
            if (game.getCurrentPlayer().getDeckCards().size() == 0) {
                JOptionPane.showMessageDialog(null,
                        "Your deck is empty.Continue game with your hand's cards", "Error", JOptionPane.ERROR_MESSAGE);

                return null;

            } else {
                Cards card = game.getCurrentPlayer().getDeckCards().get(0);
                game.getCurrentPlayer().getDeckCards().remove(0);
                return card;

            }
        } else {
            game.getCurrentPlayer().getDeckCards().remove(0);
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

    public static String playMinion(Minion playingCard, Game game, int k) {
//        System.out.println(playingCard);
        String result = "";
        boolean minionPlayed = false;
        if (k != 7) {
            if (game.getCurrentPlayer().getBattleGroundCards().size() >= k) {
//                ArrayList<Minion> copy = new ArrayList<>(Game.getInstance().getCurrentPlayer().getBattleGroundCards());
                ArrayList<Minion> copy = (ArrayList<Minion>) game.getCurrentPlayer().getBattleGroundCards().clone();
                game.getCurrentPlayer().getBattleGroundCards().clear();
                boolean isAdded = false;
                for (int j = 0; j < copy.size(); j++) {
                    if (j == (k - 1) && !isAdded) {
                        System.out.println(playingCard);
                        System.out.println(playingCard.copy());
                        game.getCurrentPlayer().getBattleGroundCards().add(playingCard.copy());
                        isAdded = true;
                        j--;
                    } else {
                        game.getCurrentPlayer().getBattleGroundCards().add(copy.get(j).copy());
                    }
                }
                game.getMapper().setAddedBeforeForBeingBetween(true);
                minionPlayed = true;
            }
        } else {
            game.getCurrentPlayer().getBattleGroundCards().add(playingCard.copy());
            game.getMapper().setAddedBeforeForBeingBetween(false);
            minionPlayed = true;

            game.getFormerPlayer().getHero().getHeroPower().accept(new SummonVisitorOfHeroPowers(),
                    null, null, null,
                    null, null, null,
                    null, null, null,
                    game.getCurrentPlayer().getBattleGroundCards().
                            get(game.getCurrentPlayer().getBattleGroundCards().size() - 1), game);


            for (Cards card : game.getFormerPlayer().getBattleGroundCards()) {
                card.accept(new EnemySummonVisitor(), null, null, null,
                        null, null,
                        game.getCurrentPlayer().getBattleGroundCards().
                                get(game.getCurrentPlayer().getBattleGroundCards().size() - 1),
                        null, null, game);
            }


            if (game.getCurrentPlayer().getBattleGroundCards().size() > (k - 1)) {
                for (Minion minion : game.getCurrentPlayer().getBattleGroundCards()) {
                    minion.accept(new SummonVisitor(), game.getCurrentPlayer().getBattleGroundCards(),
                            game.getCurrentPlayer().getHandsCards(), new ArrayList<Cards>(), null,
                            null, game.getCurrentPlayer().getBattleGroundCards().get(k - 1),
                            null, null, game);//todo json
                }
            }

        }
        if (minionPlayed) {
            result = playCard(playingCard, game);
            playingCard.accept(new BattleCryVisitor(), game.getCurrentPlayer().getBattleGroundCards(),
                    game.getCurrentPlayer().getHandsCards(), new ArrayList<Cards>(), playingCard,
                    null, null, null, null, game);//todo


            if (k != 7) {

                if (game.getCurrentPlayer().getBattleGroundCards().size() > (k - 1)) {
                    for (Minion minion : game.getCurrentPlayer().getBattleGroundCards()) {
                        minion.accept(new SummonVisitor(), game.getCurrentPlayer().getBattleGroundCards(),
                                game.getCurrentPlayer().getHandsCards(),
                                new ArrayList<Cards>(), null, null,
                                game.getCurrentPlayer().getBattleGroundCards().get(k - 1), null, null, game);
                        //todo json
                    }

                }

                if (game.getCurrentPlayer().getBattleGroundCards().size() > (k - 1)) {
                    game.getFormerPlayer().getHero().getHeroPower().accept(new SummonVisitorOfHeroPowers(),
                            null, null, null,
                            null, null, null,
                            null, null, null,
                            game.getCurrentPlayer().getBattleGroundCards().get(k - 1), game);
                }

                if (game.getCurrentPlayer().getBattleGroundCards().size() > (k - 1)) {
                    for (Cards card : game.getFormerPlayer().getBattleGroundCards()) {
                        card.accept(new EnemySummonVisitor(), null, null, null,
                                null, null, game.getCurrentPlayer().getBattleGroundCards().get(k - 1),
                                null, null, game);
                    }
                }


            }
        }
        return result;
    }

    public static String playSpell(Cards playingCard, Game game) {
        String result = "";
        result = playCard(playingCard, game);

        playingCard.accept(new BattleCryVisitor(), game.getCurrentPlayer().getBattleGroundCards(),
                game.getCurrentPlayer().getHandsCards(), new ArrayList<Cards>(), null,
                null, null, null, null, game);

        playingCard.accept(new DrawCardVisitor(), game.getCurrentPlayer().getBattleGroundCards(),
                game.getCurrentPlayer().getHandsCards(), new ArrayList<Cards>(),null,
                null, null, null, null, game);

        playingCard.accept(new ActionVisitor(), game.getCurrentPlayer().getBattleGroundCards(),
                game.getCurrentPlayer().getHandsCards(),
                game.getCurrentPlayer().getDeckCards(), null, null,
               null, null, null, game);

        playingCard.accept(new TargetVisitor(), game.getCurrentPlayer().getBattleGroundCards(),
                game.getCurrentPlayer().getHandsCards(), game.getCurrentPlayer().getDeckCards(),
                null, null,null, null, null, game);//todo ...............
        //todo json
        return result;
    }

    public static String playWeapon(Cards playingCard, Game game) {
        String result = "";
        game.getCurrentPlayer().setCurrentWeapon((Weapon) playingCard);
        result = playCard(playingCard, game);
        playingCard.accept(new BattleCryVisitor(), game.getCurrentPlayer().getBattleGroundCards(),
                game.getCurrentPlayer().getHandsCards(), new ArrayList<Cards>(), null,
                null, null, null, null, game);


        return result;
    }



    public static boolean checkThatCanReleaseCard(int x, int y) {//todo
        return x > 0 && x < 1200 && y > 385 && y < 770;
    }

    public static String getTypeOfGivenCard(String cardName) {
        for (Cards cards : Cards.getAllCards()) {
            if (cards.getName().equals(cardName)) {
                return cards.getType();
            }
        }
        return null;
    }


    public static Heroes getTargetOfHeroPowerWitchIsHero(Game game) {
        if (game.getTargetOfHeroPower() == -2) {
            if (game.getTargetAllianceOfHeroPower().equals(Alliance.WHITE)) {
                return game.getWhitePlayer().getHero();
            } else if (game.getTargetAllianceOfHeroPower().equals(Alliance.BLACK)) {
                return game.getBlackPlayer().getHero();
            }
        }

        return null;

    }


    public static Minion getTargetOfHeroPower(Game game) {

        if (game.getTargetAllianceOfHeroPower() == null) {
            return null;
        } else if (game.getTargetOfHeroPower() <= 0) {
            return null;
        }

        if (game.getTargetAllianceOfHeroPower().equals(Alliance.WHITE)) {
            return game.getWhitePlayer().getBattleGroundCards().get(game.getTargetOfHeroPower() - 1);
        } else if (game.getTargetAllianceOfHeroPower().equals(Alliance.BLACK)) {
            return game.getBlackPlayer().getBattleGroundCards().get(game.getTargetOfHeroPower() - 1);
        }
        return null;

    }

    public static Minion getTargetOfSpell(Game game) {

        if (game.getAllianceOfSpellsTarget() == null) {
            return null;
        } else if (game.getTargetOfSpell() <= 0) {
            return null;
        }
        if (game.getAllianceOfSpellsTarget().equals(Alliance.WHITE)) {
            return game.getWhitePlayer().getBattleGroundCards().get(game.getTargetOfSpell() - 1);
        } else if (game.getAllianceOfSpellsTarget().equals(Alliance.BLACK)) {
            return game.getBlackPlayer().getBattleGroundCards().get(game.getTargetOfSpell() - 1);
        }
        return null;
    }

    public static void setTargetOfSpell(int number, Alliance alliance, Game game) {
        game.setTargetOfSpell(number);
        game.setAllianceOfSpellsTarget(alliance);
    }

    public static void setTargetForHeroPower(int number, Alliance alliance, Game game) {
        game.setTargetOfHeroPower(number);
        game.setTargetAllianceOfHeroPower(alliance);
    }

    public static void setAttacker(int attacker, Game game) {
        game.setAttacker(attacker);
    }

    public static void setTarget(int target, Game game) {
        game.setTarget(target);
    }

    public static void setAllianceOfTarget(Alliance alliance, Game game) {
        game.setTargetAlliance(alliance);
    }

    public static void setAllianceAttacker(Alliance alliance, Game game) {
        game.setAttackerAlliance(alliance);
    }

    public static Alliance getAllianceOfTarget(Game game) {
        return game.getTargetAlliance();
    }

    public static Alliance getAllianceOfAttacker(Game game) {
        return game.getAttackerAlliance();
    }

    public static int getAttacker(Game game) {
        return game.getAttacker();
    }

    public static int getTarget(Game game) {
        return game.getTarget();
    }

    public static Alliance getAlliance(int yCoordinateOfCard) {

        Alliance allianceOfSpellTarget;
        if (yCoordinateOfCard <= 385) {
            allianceOfSpellTarget = Alliance.BLACK;
        } else {
            allianceOfSpellTarget = Alliance.WHITE;
        }
        return allianceOfSpellTarget;
    }

    public static Heroes getTargetOfSpellWitchIsHero(Game game) {//it actually doesnt use
        if (game.getAllianceOfSpellsTarget().equals(Alliance.WHITE)) {
            return game.getWhitePlayer().getHero();
        } else if (game.getAllianceOfSpellsTarget().equals(Alliance.BLACK)) {
            return game.getBlackPlayer().getHero();
        }

        return null;

    }

    public static ArrayList<Cards> getDeckCards(Game game) {
        return game.getCurrentPlayer().getDeckCards();
    }

    public static HeroPower getHeroPower(Game game) {
        return game.getCurrentPlayer().getHero().getHeroPower();
    }

    public static Cards getPlyingCardOfGameState(Game game) {
        return game.getPlayingCard();
    }

    public static ArrayList<Minion> getBattleGround(Game game) {
        return game.getCurrentPlayer().getBattleGroundCards();
    }

    public static ArrayList<Cards> getHandCards(Game game) {
        return game.getCurrentPlayer().getHandsCards();
    }

    public static int getNumber(int xCoordinateOfCard) {
        int number = 0;

        if (xCoordinateOfCard > 45 && xCoordinateOfCard < 150) {
            number = 1;

        } else if (xCoordinateOfCard > 190 && xCoordinateOfCard < 295) {
            number = 2;

        } else if (xCoordinateOfCard > 335 && xCoordinateOfCard < 440) {
            number = 3;

        } else if (xCoordinateOfCard > 480 && xCoordinateOfCard < 585) {
            number = 4;

        } else if (xCoordinateOfCard > 625 && xCoordinateOfCard < 730) {
            number = 5;

        } else if (xCoordinateOfCard > 770 && xCoordinateOfCard < 830) {
            number = 6;

        } else if (xCoordinateOfCard > 915 && xCoordinateOfCard < 1010) {
            number = 7;

        }
        return number;
    }


    public static int giveHeroHp(Alliance alliance, Game game) {
        if (alliance.equals(Alliance.WHITE)) {
            return game.getWhitePlayer().getHero().getHealthPower();
        } else if (alliance.equals(Alliance.BLACK)) {
            return game.getBlackPlayer().getHero().getHealthPower();
        }
        return -888888;
    }

    public static int giveHeroAttackPower(Alliance alliance, Game game) {
        if (alliance.equals(Alliance.WHITE)) {
            return game.getWhitePlayer().getHero().getAttackPower();
        } else if (alliance.equals(Alliance.BLACK)) {
            return game.getBlackPlayer().getHero().getAttackPower();
        }
        return -888888;
    }

    public static int giveWeaponDurability(Alliance alliance, Game game) {

        if (alliance.equals(Alliance.WHITE)) {
            return game.getWhitePlayer().getCurrentWeapon().getDurability();

        } else if (alliance.equals(Alliance.BLACK)) {
            return game.getBlackPlayer().getCurrentWeapon().getDurability();
        }
        return -555555;
    }

    public static int giveWeaponAttackPower(Alliance alliance, Game game) {
        if (alliance.equals(Alliance.WHITE)) {
            return game.getWhitePlayer().getCurrentWeapon().getAttackPower();

        } else if (alliance.equals(Alliance.BLACK)) {
            return game.getBlackPlayer().getCurrentWeapon().getAttackPower();
        }
        return -555555;
    }

    public static int giveMinionHpWithName(int numberOfCardInBattleGround, Alliance alliance, Game game) {
        if (alliance.equals(Alliance.WHITE)) {
            return game.getWhitePlayer().getBattleGroundCards().get(numberOfCardInBattleGround - 1).getHealthPower();

        } else if (alliance.equals(Alliance.BLACK)) {
            return game.getBlackPlayer().getBattleGroundCards().get(numberOfCardInBattleGround - 1).getHealthPower();
        }
        return -55555555;
    }

    public static int giveMinionAttackWithName(int numberOfCardInBattleGround, Alliance alliance, Game game) {

        if (alliance.equals(Alliance.WHITE)) {
            return game.getWhitePlayer().getBattleGroundCards().get(numberOfCardInBattleGround - 1).getAttackPower();

        } else if (alliance.equals(Alliance.BLACK)) {
            return game.getBlackPlayer().getBattleGroundCards().get(numberOfCardInBattleGround - 1).getAttackPower();
        }
        return -66666666;
    }


    //********************************************************************

    public static String playHeroPower(Game game) {
        String result = "";
        if (game.getCurrentPlayer().getMana() < game.getCurrentPlayer().getHero().getHeroPower().getMana()) {
//            JOptionPane.showMessageDialog(null, "You don't have enough mana");
            result = "You don't have enough mana";
            return result;
        }


        game.getCurrentPlayer().setMana(game.getCurrentPlayer().getMana() -
                game.getCurrentPlayer().getHero().getHeroPower().getMana());

        //for heroPowers witch doesnt need target

        game.getCurrentPlayer().getHero().getHeroPower().accept(new HeroPowerVisitor(),
                game.getCurrentPlayer(),
                game.getCurrentPlayer().getBattleGroundCards(),
                game.getFormerPlayer().getBattleGroundCards(),
                game.getCurrentPlayer().getHandsCards(),
                game.getFormerPlayer().getHandsCards(),
                game.getCurrentPlayer().getDeckCards(),
                game.getFormerPlayer().getDeckCards(),
              null, null, null, game);//todo json

        //for heroPowers witch need target
        System.out.println("playHeroPower");
        game.getCurrentPlayer().getHero().getHeroPower().accept(new TargetVisitorOfPowers(),
                game.getCurrentPlayer(),
                game.getCurrentPlayer().getBattleGroundCards(),
                game.getFormerPlayer().getBattleGroundCards(),
                game.getCurrentPlayer().getHandsCards(),
                game.getFormerPlayer().getHandsCards(),
                game.getCurrentPlayer().getDeckCards(),
                game.getFormerPlayer().getDeckCards(),
                null, null, null, game);//todo json

        return result;
    }


    public static String attack(int attacker, int target, Alliance attackerAlliance, Alliance targetAlliance, Game game) {

        String result = "NotEnd";

        if (attacker == -5) {
            return result;
        } else if (target == -5) {
            return result;
        }


        if (attacker == -2) {//Weapon

            if (attackerAlliance.equals(targetAlliance)) {
                return result;
            }

            if (target == -3) { //weapon vs hero
                Heroes hero = game.getFormerPlayer().getHero();
                Weapon weapon = game.getCurrentPlayer().getCurrentWeapon();
                if (!weapon.isHasAttackInThisTurn() && hero.getCanBeAttacked()) {
                    hero.setHealthPower(hero.getHealthPower() - weapon.getAttackPower());
                    weapon.setDurability(weapon.getDurability() - 1);
                    weapon.setHasAttackInThisTurn(true);
                    result = removeDeadCharacters(game);
//                    writeOnLogPanel(weapon.getName() + " Attack " + hero.getName());
                }
            } else {// weapon vs minion
                Weapon weapon = game.getCurrentPlayer().getCurrentWeapon();
                Minion minion = game.getFormerPlayer().getBattleGroundCards().get(target);
                if (!weapon.isHasAttackInThisTurn() && minion.getCanBeAttacked()) {
                    if (!minion.isDivineShield()) {
                        minion.setHealthPower(minion.getHealthPower() - weapon.getAttackPower());
                        game.getCurrentPlayer().getHero().setHealthPower
                                (game.getCurrentPlayer().getHero().getHealthPower() - minion.getAttackPower());

                        weapon.setDurability(weapon.getDurability() - 1);
                        weapon.setHasAttackInThisTurn(true);

                    } else {
                        minion.setDivineShield(false);
                        game.getCurrentPlayer().getHero().setHealthPower
                                (game.getCurrentPlayer().getHero().getHealthPower() - minion.getAttackPower());
                        weapon.setDurability(weapon.getDurability() - 1);
                        weapon.setHasAttackInThisTurn(true);
                    }
                    minion.accept(new GetDamageVisitor(), game.getFormerPlayer().getBattleGroundCards(),
                            null, null, minion, null, null,
                            null, null, game);

                    result = removeDeadCharacters(game);
//                    Mapper.writeOnLogPanel(weapon.getName() + " Attack " + minion.getName());
                }
            }
        } else {

            if (target == -3) {//target is hero    minion vs hero
                Minion minion = game.getCurrentPlayer().getBattleGroundCards().get(attacker);
                Heroes hero = game.getFormerPlayer().getHero();
                if (minion.getIsActive() && !minion.getHasAttackInThisTurn() && hero.getCanBeAttacked()) {
                    hero.setHealthPower(hero.getHealthPower() - minion.getAttackPower());
                    minion.setHasAttackInThisTurn(true);
                    result = removeDeadCharacters(game);
//                    Mapper.writeOnLogPanel(minion.getName() + " Attack " + hero.getName());
                }
            } else {//minion vs minion

                if (attackerAlliance.equals(targetAlliance)) {
                    return result;
                }

                Minion minion = game.getCurrentPlayer().getBattleGroundCards().get(attacker);
                Minion minion2 = game.getFormerPlayer().getBattleGroundCards().get(target);

                if (minion.getIsActive() && !minion.getHasAttackInThisTurn()) {
                    if (minion2.getCanBeAttacked()) {

                        if (minion2.isDivineShield()) {
                            minion2.setDivineShield(false);
                            minion.setHealthPower(minion.getHealthPower() - minion2.getAttackPower());

                        } else {
                            minion.setHealthPower(minion.getHealthPower() - minion2.getAttackPower());
                            minion2.setHealthPower(minion2.getHealthPower() - minion.getAttackPower());
                        }
                        minion.setHasAttackInThisTurn(true);
                        result = removeDeadCharacters(game);
//                        Mapper.writeOnLogPanel(minion.getName() + " Attack " + minion2.getName());


                        minion2.accept(new GetDamageVisitor(), game.getFormerPlayer().getBattleGroundCards(),
                                null, null, minion, null, null,
                                null, null, game);

                    } else {
                        //TODO you first need to destroy Taunt OR you cant attack to this minion
                    }
                } else {
                    //TODO you cant attack with this minion in this turn``````````````````````````````````````````````````````````````````````````````````````
                }
            }
        }
        return result;
    }


    public static String removeDeadCharacters(Game game) {

        String result = "NotEnd";

        if (game.getWhitePlayer().getCurrentWeapon() != null) {
            if (game.getWhitePlayer().getCurrentWeapon().getDurability() == 0) {
                game.getWhitePlayer().setCurrentWeapon(null);
            }
        }

        if (game.getBlackPlayer().getCurrentWeapon() != null) {
            if (game.getBlackPlayer().getCurrentWeapon().getDurability() == 0) {
                game.getBlackPlayer().setCurrentWeapon(null);
            }
        }

        if (game.getBlackPlayer().getHero().getHealthPower() <= 0) {
            game.getWhitePlayer().getDeck().
                    setNumberOfUses(game.getWhitePlayer().getDeck().getNumberOfUses() + 1);

            game.getBlackPlayer().getDeck().
                    setNumberOfUses(game.getBlackPlayer().getDeck().getNumberOfUses() + 1);

            game.getWhitePlayer().getDeck().
                    setNumberOfWins(game.getWhitePlayer().getDeck().getNumberOfWins() + 1);


            game.getWhitePlayer().getDeck().setCups(game.getWhitePlayer().getDeck().getCups()+1);
            game.getBlackPlayer().getDeck().setCups(game.getBlackPlayer().getDeck().getCups()-1);

            game.getBlackPlayer().getPlayer().setNumOfCups(Math.max(0,(game.getBlackPlayer().getPlayer().getNumOfCups()-1)));
            game.getWhitePlayer().getPlayer().setNumOfCups(game.getWhitePlayer().getPlayer().getNumOfCups()+1);

            result = "Friendly Player wins!";
//            JOptionPane.showMessageDialog(MyMainFrame.getInstance(),
//                    "Friendly Player wins!", "End Match", JOptionPane.INFORMATION_MESSAGE);
        } else if (game.getWhitePlayer().getHero().getHealthPower() <= 0) {

            game.getWhitePlayer().getDeck().
                    setNumberOfUses(game.getWhitePlayer().getDeck().getNumberOfUses() + 1);

            game.getBlackPlayer().getDeck().
                    setNumberOfUses(game.getBlackPlayer().getDeck().getNumberOfUses() + 1);

            game.getBlackPlayer().getDeck().
                    setNumberOfWins(game.getBlackPlayer().getDeck().getNumberOfWins() + 1);

            game.getBlackPlayer().getDeck().setCups(game.getBlackPlayer().getDeck().getCups()+1);
            game.getWhitePlayer().getDeck().setCups(game.getWhitePlayer().getDeck().getCups()-1);

            game.getWhitePlayer().getPlayer().setNumOfCups(Math.max(0,(game.getWhitePlayer().getPlayer().getNumOfCups()-1)));
            game.getBlackPlayer().getPlayer().setNumOfCups(game.getBlackPlayer().getPlayer().getNumOfCups()+1);

            result = "Enemy Player wins!";

//            JOptionPane.showMessageDialog(MyMainFrame.getInstance(),
//                    "Enemy Player wins!", "End Match", JOptionPane.INFORMATION_MESSAGE);
        }
        game.getWhitePlayer().getBattleGroundCards().removeIf(minion -> minion.getHealthPower() <= 0);
        game.getBlackPlayer().getBattleGroundCards().removeIf(minion -> minion.getHealthPower() <= 0);
        setCanBeAttacked(game.getWhitePlayer());
        setCanBeAttacked(game.getBlackPlayer());
        return result;
    }


    public static void setFriendlyInfoPassiveOfGameState(InGamePlayer player, int numberOfPassive) {
        player.setInfoPassive(player.getPassivesToChoose().get(numberOfPassive));
        player.getInfoPassive().accept(new InfoPassiveVisitor(), player,
                player.getBattleGroundCards(),
                player.getHandsCards(),
                player.getDeckCards(), Server.giveGameWithPlayer(player.getPlayer().getUserName()));

    }


    public static ArrayList<String> giveNameOfCardsList(ArrayList<? extends Cards> cards) {
        ArrayList<String> handsCardNames = new ArrayList<>();
        for (Cards card : cards) {
            handsCardNames.add(card.getName());
        }
        return handsCardNames;
    }

    public static ArrayList<String> giveNameOfCardList(List<Cards> cards) {
        ArrayList<String> handsCardNames = new ArrayList<>();
        for (Cards card : cards) {
            handsCardNames.add(card.getName());
        }
        return handsCardNames;
    }

    public static void setPlayingCardOfGameState(String cardName, Game game) {
        for (Cards cards : Cards.getAllCards()) {
            if (cards.getName().equals(cardName)) {
                game.setPlayingCard(cards.copy());
            }
        }
    }


    public static boolean canAddMinionToBattleGround(Game game) {
        return game.getCurrentPlayer().getBattleGroundCards().size() <= 7 - 1;
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
                game.getCurrentPlayer().getHandsCards(), game.getCurrentPlayer().getDeckCards(),
                Server.giveGameWithPlayer(player.getPlayer().getUserName()));
        if (game.getCurrentPlayer().getHandsCards().size() < 12) {
            if (game.getCurrentPlayer().getDeckCards().size() == 0) {
                message = "Empty";
            } else {
                game.getCurrentPlayer().getHandsCards().add(game.getCurrentPlayer().getDeckCards().get(0));

                game.getCurrentPlayer().getDeckCards().remove(0);

            }
        } else {
            game.getCurrentPlayer().getDeckCards().remove(0);
            message = "Full";
        }

        for (Minion minion : game.getCurrentPlayer().getBattleGroundCards()) {
            minion.accept(new DrawCardVisitor(), game.getCurrentPlayer().getBattleGroundCards(),
                    game.getCurrentPlayer().getHandsCards(), new ArrayList<Cards>(), minion, null,null,
                    null, null, game);
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
//        game.getMyTimer().reStart();
        game.getCurrentPlayer().setTurn(game.getCurrentPlayer().getTurn() + 1);
        game.getCurrentPlayer().setMana((int) Math.min(game.getCurrentPlayer().getTurn(), 10));
        game.changeTurn();
        game.getCurrentPlayer().getMyTimer().reStart();
    }


    public static String endTurn(InGamePlayer player) {
        String message = "Successful";
        setCanBeAttacked(player);
        setIsActives(player);
        player.getPlayer().setPlayerStatusInGame(Status.PLAY_PAGE);
        player.getMyTimer().pause();
//        System.out.println(Game.getInstance().getCurrentPlayer().getBattleGroundCards());
        Iterator<Minion> itr = player.getBattleGroundCards().iterator();

        while (itr.hasNext()) {
            Minion minion = itr.next();
            minion.accept(new EndTurnVisitor(), player.getBattleGroundCards(),
                    player.getHandsCards(), new ArrayList<Cards>(), minion, null,
                   null, null, null,
                    Server.giveGameWithPlayer(player.getPlayer().getUserName()));//todo

        }
        nextTurn(player);
        message = drawCard(player);
//        ControllerOfMainComponents.currentPlayer.getLoggerOfMyPlayer().info("End turn");
        return message;
    }


}
