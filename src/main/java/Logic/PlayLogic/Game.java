package Logic.PlayLogic;


import Logic.MyTimer;
import Models.Cards.CardClasses.Cards;
import Models.Deck.Deck;
import Models.Heroes.Mage;
import Models.Player.InGamePlayer;
import Visitors.PowerVisitor.SpVisitor.SpecialPowerVisitor;

import java.io.IOException;

public class Game {

    private InGamePlayer friendlyPlayer;
    private InGamePlayer enemyPlayer;
    private InGamePlayer currentPlayer;
    private InGamePlayer formerPlayer;
    private Alliance currentAlliance;
    private Cards playingCard;
    private int gameMode;
    private MyTimer myTimer;

    private int attacker;
    private int target;

    private Alliance attackerAlliance;
    private Alliance targetAlliance;

    private int targetOfSpell;
    private Alliance allianceOfSpellsTarget;

    private int targetOfHeroPower;
    private Alliance targetAllianceOfHeroPower;





    private static Game game;

    static {
        try {
            game = new Game();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Game getInstance() {
        return game;
    }


    public Game() throws IOException {
        myTimer = new MyTimer();
        myTimer.start();
//        initGameState();

    }


    public void initGameState() throws IOException {

        if (gameMode == 1) {//Normal Game //TODO for next Phase
            DeckReader deckReader = new DeckReader("src/main/resources/DeckReader/DeckReader.properties");
            friendlyPlayer = new InGamePlayer(ControllerOfMainComponents.currentPlayer);
            enemyPlayer = new InGamePlayer();
            enemyPlayer.setHero(new Mage());
            enemyPlayer.setDeckCards(deckReader.getDeck("ENEMY"));
            enemyPlayer.initHandsCards();
            playingCard = new Cards();
            currentPlayer = friendlyPlayer;
            formerPlayer = enemyPlayer;
            currentAlliance = Alliance.FRIENDLY;

        } else if (gameMode == 2) {//Training Game //TODO has positive point
            DeckReader deckReader = new DeckReader("src/main/resources/DeckReader/DeckReader.properties");
            friendlyPlayer = new InGamePlayer(ControllerOfMainComponents.currentPlayer);
            enemyPlayer = new InGamePlayer();
            enemyPlayer.setHero(new Mage());
            enemyPlayer.setDeckCards(deckReader.getDeck("ENEMY"));
            playingCard = new Cards();
            currentPlayer = friendlyPlayer;
            formerPlayer = enemyPlayer;
            currentAlliance = Alliance.FRIENDLY;

        } else if (gameMode == 3) {//DeckReader
            DeckReader deckReader = new DeckReader("src/main/resources/DeckReader/DeckReader.properties");
            friendlyPlayer = new InGamePlayer();
            friendlyPlayer.setDeckCards(deckReader.getDeck("FRIEND"));
            friendlyPlayer.setHero(new Mage());
            friendlyPlayer.initPassiveToChoose();
            friendlyPlayer.initHandsCards();
            enemyPlayer = new InGamePlayer();
            enemyPlayer.setDeckCards(deckReader.getDeck("ENEMY"));
            enemyPlayer.setHero(new Mage());
            enemyPlayer.initHandsCards();
            playingCard = new Cards();
            currentPlayer = friendlyPlayer;
            formerPlayer = enemyPlayer;
            currentAlliance = Alliance.FRIENDLY;

        } else if (gameMode == 4) {//Two Player Game
            DeckReader deckReader = new DeckReader("src/main/resources/DeckReader/DeckReader.properties");
            friendlyPlayer = new InGamePlayer(ControllerOfMainComponents.currentPlayer);
            enemyPlayer = new InGamePlayer();
            enemyPlayer.setHero(new Mage());
            enemyPlayer.setDeckCards(deckReader.getDeck("ENEMY"));
            enemyPlayer.initHandsCards();



            //todo choose passive for enemy
//            enemyPlayer.getInfoPassive().accept(new InfoPassiveVisitor(), enemyPlayer,
//                    enemyPlayer.getBattleGroundCards(), enemyPlayer.getHandsCards(), enemyPlayer.getDeckCards());


            friendlyPlayer.getHero().accept(new SpecialPowerVisitor(), friendlyPlayer, friendlyPlayer.getBattleGroundCards(),
                    enemyPlayer.getBattleGroundCards(), friendlyPlayer.getHandsCards(), enemyPlayer.getHandsCards(),
                    friendlyPlayer.getDeckCards(), enemyPlayer.getDeckCards(), null, null, null);

            enemyPlayer.getHero().accept(new SpecialPowerVisitor(), enemyPlayer, enemyPlayer.getBattleGroundCards(),
                    friendlyPlayer.getBattleGroundCards(), enemyPlayer.getHandsCards(), friendlyPlayer.getHandsCards(),
                    enemyPlayer.getDeckCards(), friendlyPlayer.getDeckCards(), null, null, null);
            playingCard = new Cards();
            currentPlayer = friendlyPlayer;
            formerPlayer = enemyPlayer;
            currentAlliance = Alliance.FRIENDLY;
        }

    }

    public void changeAlliance() {
        if (currentAlliance.equals(Alliance.FRIENDLY)) {
            currentAlliance = Alliance.ENEMY;
        } else {
            currentAlliance = Alliance.FRIENDLY;
        }
    }


    //getters and setters
    //**********************

    public int getGameMode() {
        return gameMode;
    }
    public void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }
    public Cards getPlayingCard() {
        return playingCard;
    }
    public void setPlayingCard(Cards playingCard) {
        this.playingCard = playingCard;
    }
    public InGamePlayer getFriendlyPlayer() {
        return friendlyPlayer;
    }
    public void setFriendlyPlayer(InGamePlayer friendlyPlayer) {
        this.friendlyPlayer = friendlyPlayer;
    }
    public InGamePlayer getEnemyPlayer() {
        return enemyPlayer;
    }
    public void setEnemyPlayer(InGamePlayer enemyPlayer) {
        this.enemyPlayer = enemyPlayer;
    }
    public InGamePlayer getCurrentPlayer() {
        return currentPlayer;
    }
    public void setCurrentPlayer(InGamePlayer currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    public Alliance getCurrentAlliance() {
        return currentAlliance;
    }
    public InGamePlayer getFormerPlayer() {
        return formerPlayer;
    }
    public void setFormerPlayer(InGamePlayer formerPlayer) {
        this.formerPlayer = formerPlayer;
    }
    public void setCurrentAlliance(Alliance currentAlliance) {
        this.currentAlliance = currentAlliance;
    }
    public MyTimer getMyTimer() {
        return myTimer;
    }
    public void setMyTimer(MyTimer myTimer) {
        this.myTimer = myTimer;
    }
    public int getTargetOfHeroPower() {
        return targetOfHeroPower;
    }
    public void setTargetOfHeroPower(int targetOfHeroPower) {
        this.targetOfHeroPower = targetOfHeroPower;
    }
    public Alliance getTargetAllianceOfHeroPower() {
        return targetAllianceOfHeroPower;
    }
    public void setTargetAllianceOfHeroPower(Alliance targetAllianceOfHeroPower) {
        this.targetAllianceOfHeroPower = targetAllianceOfHeroPower;
    }
    public Alliance getAttackerAlliance() {
        return attackerAlliance;
    }
    public void setAttackerAlliance(Alliance attackerAlliance) {
        this.attackerAlliance = attackerAlliance;
    }
    public Alliance getTargetAlliance() {
        return targetAlliance;
    }
    public void setTargetAlliance(Alliance targetAlliance) {
        this.targetAlliance = targetAlliance;
    }
    public Alliance getAllianceOfSpellsTarget() {
        return allianceOfSpellsTarget;
    }
    public void setAllianceOfSpellsTarget(Alliance allianceOfSpellsTarget) {
        this.allianceOfSpellsTarget = allianceOfSpellsTarget;
    }
    public int getTargetOfSpell() {
        return targetOfSpell;
    }
    public void setTargetOfSpell(int targetOfSpell) {
        this.targetOfSpell = targetOfSpell;
    }
    public int getAttacker() {
        return attacker;
    }
    public void setAttacker(int attacker) {
        this.attacker = attacker;
    }
    public int getTarget() {
        return target;
    }
    public void setTarget(int target) {
        this.target = target;
    }

}
