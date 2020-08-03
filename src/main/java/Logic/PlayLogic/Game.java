package Logic.PlayLogic;


import Logic.MyTimer;
import Models.Cards.CardClasses.Cards;
import Models.Heroes.Mage;
import Models.Player.InGamePlayer;
import Visitors.PowerVisitor.SpVisitor.SpecialPowerVisitor;


public class Game {


    private static Object[] gameModes = new Object[]{"Normal Game", "Two Player", "Training Game", "Deck Reader"};


    private InGamePlayer whitePlayer;
    private InGamePlayer blackPlayer;

    private InGamePlayer currentPlayer;
    private InGamePlayer formerPlayer;
    private Alliance currentAlliance;
    private Alliance formerAlliance;

    private Cards playingCard;
    private String gameMode;
    private MyTimer myTimer;

    private int attacker;
    private int target;

    private Alliance attackerAlliance;
    private Alliance targetAlliance;

    private int targetOfSpell;
    private Alliance allianceOfSpellsTarget;

    private int targetOfHeroPower;
    private Alliance targetAllianceOfHeroPower;


    public Game(InGamePlayer whitePlayer, InGamePlayer blackPlayer) {
        initGameState(whitePlayer, blackPlayer);
        myTimer = new MyTimer();
        myTimer.start();
    }


    public void initGameState(InGamePlayer whitePlayer, InGamePlayer blackPlayer) {
        if (gameMode.equalsIgnoreCase("OnlineGame")) {//OnlineGame
            this.whitePlayer = whitePlayer;
            this.blackPlayer = blackPlayer;
            playingCard = new Cards();
            currentPlayer = whitePlayer;
            formerPlayer = blackPlayer;
            currentAlliance = Alliance.WHITE;
            formerAlliance = Alliance.BLACK;
        } else if (gameMode.equalsIgnoreCase("DeckReader")) {//DeckReader
            DeckReader deckReader = new DeckReader("src/main/resources/DeckReader/DeckReader.properties");
            this.whitePlayer = whitePlayer;
            whitePlayer.setDeckCards(deckReader.getDeck("ENEMY"));
            whitePlayer.setHero(new Mage());
            whitePlayer.initPassiveToChoose();
            whitePlayer.initHandsCards();
            this.blackPlayer = blackPlayer;
            blackPlayer.setDeckCards(deckReader.getDeck("FRIEND"));
            blackPlayer.setHero(new Mage());
            blackPlayer.initHandsCards();
            playingCard = new Cards();
            currentPlayer = whitePlayer;
            formerPlayer = blackPlayer;
            currentAlliance = Alliance.WHITE;
            formerAlliance = Alliance.BLACK;
        } else if (gameMode.equalsIgnoreCase("OfflineGame")) {//OfflineGame
            DeckReader deckReader = new DeckReader("src/main/resources/DeckReader/DeckReader.properties");
            this.whitePlayer = whitePlayer;
            blackPlayer = new InGamePlayer();
            blackPlayer.setHero(new Mage());
            blackPlayer.setDeckCards(deckReader.getDeck("ENEMY"));
            blackPlayer.initHandsCards();
            playingCard = new Cards();
            currentPlayer = whitePlayer;
            formerPlayer = blackPlayer;
            currentAlliance = Alliance.WHITE;
            formerAlliance = Alliance.BLACK;
        }

        whitePlayer.getHero().accept(new SpecialPowerVisitor(), whitePlayer, whitePlayer.getBattleGroundCards(),
                blackPlayer.getBattleGroundCards(), whitePlayer.getHandsCards(), blackPlayer.getHandsCards(),
                whitePlayer.getDeckCards(), blackPlayer.getDeckCards(), null, null, null);

        blackPlayer.getHero().accept(new SpecialPowerVisitor(), blackPlayer, blackPlayer.getBattleGroundCards(),
                whitePlayer.getBattleGroundCards(), blackPlayer.getHandsCards(), whitePlayer.getHandsCards(),
                blackPlayer.getDeckCards(), whitePlayer.getDeckCards(), null, null, null);


    }


    public void changeTurn() {
        if (currentAlliance.equals(Alliance.WHITE)) {
            currentAlliance = Alliance.BLACK;
            formerAlliance = Alliance.WHITE;
            currentPlayer = blackPlayer;
            formerPlayer = whitePlayer;
        } else {
            currentAlliance = Alliance.WHITE;
            formerAlliance = Alliance.BLACK;
            currentPlayer = whitePlayer;
            formerPlayer = blackPlayer;
        }
    }


    public void changeAlliance() {
        if (currentAlliance.equals(Alliance.WHITE)) {
            currentAlliance = Alliance.BLACK;
        } else {
            currentAlliance = Alliance.WHITE;
        }
    }


    //getters and setters
    //**********************

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public Cards getPlayingCard() {
        return playingCard;
    }

    public void setPlayingCard(Cards playingCard) {
        this.playingCard = playingCard;
    }

    public InGamePlayer getWhitePlayer() {
        return whitePlayer;
    }

    public void setWhitePlayer(InGamePlayer whitePlayer) {
        this.whitePlayer = whitePlayer;
    }

    public InGamePlayer getBlackPlayer() {
        return blackPlayer;
    }

    public void setBlackPlayer(InGamePlayer blackPlayer) {
        this.blackPlayer = blackPlayer;
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

    public static Object[] getGameModes() {
        return gameModes;
    }

    public static void setGameModes(Object[] gameModes) {
        Game.gameModes = gameModes;
    }
}
