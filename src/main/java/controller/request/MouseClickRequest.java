package controller.request;


import Logic.PlayLogic.Alliance;
import Logic.PlayLogic.Game;
import Models.Cards.CardClasses.Minion;
import Models.Player.Player;
import Visitors.CardVisitors.AfterSelectVisitor;
import Visitors.PowerVisitor.HeroPowerVisitor.AfterSelectPowerVisitor;
import controller.Status;
import controller.controllers.GamePartController;
import controller.response.Response;
import database.dssds;
import server.Server;

public class MouseClickRequest extends Request {


    private String userName;
    private String cardName;
    private String typeOfCard;
    private boolean clicked;
    private int xCoordinateOfCard;
    private int yCoordinateOfCard;
    private Alliance alliance;
    private String typeOfClick;


    public MouseClickRequest(String userName, String cardName, String typeOfCard, boolean clicked,
                             int xCoordinateOfCard, int yCoordinateOfCard, Alliance alliance, String typeOfClick) {

        this.userName = userName;
        this.cardName = cardName;
        this.typeOfCard = typeOfCard;
        this.clicked = clicked;
        this.xCoordinateOfCard = xCoordinateOfCard;
        this.yCoordinateOfCard = yCoordinateOfCard;
        this.alliance = alliance;
        this.typeOfClick = typeOfClick;
    }


    @Override
    public Response execute() {
        Player player = dssds.fetchPlayer(userName);
        Game game = Server.giveGameWithPlayer(userName);
        Response response = null;
        boolean doubleClick;
        int hp;
        int attackPower;
        int numberOfCardInBattleGround;


        if (player.getPlayerStatusInGame().equals(Status.CHOOSE_TARGET_FOR_SPELL)) {
            if (typeOfClick.equalsIgnoreCase("Left")) {


                GamePartController.setTargetOfSpell(GamePartController.getNumber(xCoordinateOfCard), alliance,game);

                GamePartController.getPlyingCardOfGameState(game).accept(new AfterSelectVisitor(),
                        GamePartController.getBattleGround(game),
                        GamePartController.getHandCards(game), GamePartController.getDeckCards(game),
                        GamePartController.getTargetOfSpell(game),
                        GamePartController.getTargetOfSpellWitchIsHero(game),
                        null, null, alliance, game);

                player.setPlayerStatusInGame(Status.PLAY_PAGE);
            }
        }


        if (player.getPlayerStatusInGame().equals(Status.CHOOSE_TARGET_FOR_HERO_POWERS)) {
            if (typeOfClick.equalsIgnoreCase("Left")) {

                int number = 0;

                if (this.typeOfCard.equalsIgnoreCase("hero")) {
                    number = -2;
                    numberOfCardInBattleGround = -2;
                } else {
                    number =GamePartController.getNumber(xCoordinateOfCard);
                }

                GamePartController.setTargetForHeroPower(number, alliance, game);

                GamePartController.getHeroPower(game).accept(new AfterSelectPowerVisitor(), game.getCurrentPlayer(),
                        game.getCurrentPlayer().getBattleGroundCards(),
                        game.getFormerPlayer().getBattleGroundCards(),
                        game.getCurrentPlayer().getHandsCards(),
                        game.getFormerPlayer().getHandsCards(),
                        game.getCurrentPlayer().getDeckCards(),
                        game.getFormerPlayer().getDeckCards(),
                        GamePartController.getTargetOfHeroPower(game),
                        GamePartController.getTargetOfHeroPowerWitchIsHero(game), null, game);

                player.setPlayerStatusInGame(Status.PLAY_PAGE);
            }
        }

        if (player.getPlayerStatusInGame().equals(Status.PLAY_PAGE)) {
            if (typeOfClick.equalsIgnoreCase("Left")) {
                alliance = GamePartController.getAlliance(yCoordinateOfCard);
                int number = 0;
                numberOfCardInBattleGround = 0;
                if (this.typeOfCard.equalsIgnoreCase("heroPower")) {
                    GamePartController.playHeroPower(game);
                    number = -3;
                    numberOfCardInBattleGround = -3;
                } else {
                    if (this.typeOfCard.equalsIgnoreCase("hero")) {
                        number = -2;
                        numberOfCardInBattleGround = -2;
                        if (clicked) {
                            doubleClick = true;
                            GamePartController.setTarget(number - 1, game);
                            GamePartController.setAllianceOfTarget(alliance, game);
                            GamePartController.attack(GamePartController.getAttacker(game),
                                    GamePartController.getTarget(game), GamePartController.getAllianceOfAttacker(game),
                                    GamePartController.getAllianceOfTarget(game), game);

                            hp = GamePartController.giveMinionHpWithName(numberOfCardInBattleGround, alliance,game);
                            attackPower = GamePartController.giveMinionAttackWithName(numberOfCardInBattleGround, alliance,game);
                            clicked = false;
                            doubleClick = false;
                            GamePartController.setAttacker(-5, game);
                            GamePartController.setTarget(-5, game);
                        }
                    } else if (this.typeOfCard.equalsIgnoreCase("weapon")) {
                        number = -1;
                        numberOfCardInBattleGround = -1;
                    } else {

                        number = GamePartController.getNumber(xCoordinateOfCard);
                        numberOfCardInBattleGround = number;

                        if (clicked) {
                            doubleClick = true;
                            GamePartController.setTarget(number - 1, game);
                            GamePartController.setAllianceOfTarget(alliance, game);
                            GamePartController.attack(GamePartController.getAttacker(game),
                                    GamePartController.getTarget(game), GamePartController.getAllianceOfAttacker(game),
                                    GamePartController.getAllianceOfTarget(game), game);

                            hp = GamePartController.giveMinionHpWithName(numberOfCardInBattleGround, alliance,game);
                            attackPower = GamePartController.giveMinionAttackWithName(numberOfCardInBattleGround, alliance,game);
                            clicked = false;
                            doubleClick = false;
                            GamePartController.setAttacker(-5, game);
                            GamePartController.setTarget(-5, game);
                        }
                    }


                    if (!clicked) {
                        clicked = true;
                        GamePartController.setAttacker(number - 1, game);
                        GamePartController.setAllianceAttacker(alliance, game);
                    }

                }

            } else if (typeOfClick.equalsIgnoreCase("Right")) {
                clicked = false;
                doubleClick = false;
                GamePartController.setAttacker(-5, game);
                GamePartController.setTarget(-5, game);
                GamePartController.setAllianceAttacker(null, game);
                GamePartController.setAllianceOfTarget(null, game);
            }


        }

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

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getTypeOfCard() {
        return typeOfCard;
    }

    public void setTypeOfCard(String typeOfCard) {
        this.typeOfCard = typeOfCard;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public int getxCoordinateOfCard() {
        return xCoordinateOfCard;
    }

    public void setxCoordinateOfCard(int xCoordinateOfCard) {
        this.xCoordinateOfCard = xCoordinateOfCard;
    }

    public int getyCoordinateOfCard() {
        return yCoordinateOfCard;
    }

    public void setyCoordinateOfCard(int yCoordinateOfCard) {
        this.yCoordinateOfCard = yCoordinateOfCard;
    }

    public Alliance getAlliance() {
        return alliance;
    }

    public void setAlliance(Alliance alliance) {
        this.alliance = alliance;
    }
}
