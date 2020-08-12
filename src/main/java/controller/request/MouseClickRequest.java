package controller.request;


import Logic.PlayLogic.Alliance;
import Logic.PlayLogic.Game;
import Models.Player.Player;
import Visitors.CardVisitors.AfterSelectVisitor;
import Visitors.PowerVisitor.HeroPowerVisitor.AfterSelectPowerVisitor;
import controller.Status;
import controller.controllers.Administer;
import controller.controllers.GamePartController;
import controller.response.*;
import server.Server;

import java.util.HashMap;

import static controller.Status.COLLECTIONS_PAGE;

public class MouseClickRequest extends Request {


    private String cardName;
    private String typeOfCard;
    private boolean clicked;
    private int xCoordinateOfCard;
    private int yCoordinateOfCard;
    private Alliance alliance;
    private String typeOfClick;
    private boolean isLock;


    public MouseClickRequest(String userName, String cardName, String typeOfCard, boolean clicked,
                             int xCoordinateOfCard, int yCoordinateOfCard, Alliance alliance, String typeOfClick, boolean isLock) {

        setUserName(userName);
        setRequestType("MouseClickRequest");
        this.cardName = cardName;
        this.typeOfCard = typeOfCard;
        this.clicked = clicked;
        this.xCoordinateOfCard = xCoordinateOfCard;
        this.yCoordinateOfCard = yCoordinateOfCard;
        this.alliance = alliance;
        this.typeOfClick = typeOfClick;
        this.isLock = isLock;
    }


    @Override
    public Response execute() {

        Player player = Server.getDataBaseHandler().fetchPlayer(getUserName());

        Response response = null;
        boolean doubleClick;
        int hp;
        int attackPower;
        int numberOfCardInBattleGround;
        System.out.println("Executing mouseClick response");


        if (player.getPlayerStatusInGame().equals(Status.BUY_PAGE) ||
                player.getPlayerStatusInGame().equals(Status.SELL_PAGE)) {

            response = new ShowBuyAndSellCardResponse(cardName);
            Server.getDataBaseHandler().save(player);
            return response;
        }

        if (player.getPlayerStatusInGame().equals(Status.COLLECTIONS_PAGE)) {
            if (this.isLock) {
                player.setPlayerStatusInGame(Status.BUY_PAGE_FROM_COLLECTION);
                response = new GoToPageResponse("ShopPage");
                Server.getDataBaseHandler().save(player);
                return response;
            } else {
                response = new ShowJOptionPaneResponse("You can't Buy this card:((");
                Server.getDataBaseHandler().save(player);
                return response;
            }
        }


        if (player.getPlayerStatusInGame().equals(Status.MAKE_DECK) ||
                player.getPlayerStatusInGame().equals(Status.CHANGE_DECK)) {
            Administer.addGivenCardToCollectionDeck(player, cardName, isLock);//todo
            response = new AddCardToDeckResponse(player.getUserName(), player.getDeckToChange().getUsesHashMap());
            Server.getDataBaseHandler().save(player);
            return response;
        }


        if (player.getPlayerStatusInGame().equals(Status.CHOOSE_TARGET_FOR_SPELL)) {
            if (typeOfClick.equalsIgnoreCase("Left")) {

                Game game = Server.giveGameWithPlayer(getUserName());
                GamePartController.setTargetOfSpell(GamePartController.getNumber(xCoordinateOfCard), alliance, game);

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
                Game game = Server.giveGameWithPlayer(getUserName());
                int number = 0;

                if (this.typeOfCard.equalsIgnoreCase("hero")) {
                    number = -2;
                    numberOfCardInBattleGround = -2;
                } else {
                    number = GamePartController.getNumber(xCoordinateOfCard);
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
                Game game = Server.giveGameWithPlayer(getUserName());
                if (this.typeOfCard.equalsIgnoreCase("heroPower")) {
                    GamePartController.playHeroPower(game);
                    player.setPlayerStatusInGame(game.getCurrentPlayer().getPlayer().getPlayerStatusInGame());
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

//                            hp = GamePartController.giveMinionHpWithName(numberOfCardInBattleGround, alliance, game);
//                            attackPower = GamePartController.giveMinionAttackWithName(numberOfCardInBattleGround, alliance, game);
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

//                            hp = GamePartController.giveMinionHpWithName(numberOfCardInBattleGround, alliance, game);
//                            attackPower = GamePartController.giveMinionAttackWithName(numberOfCardInBattleGround, alliance, game);
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
                Game game = Server.giveGameWithPlayer(getUserName());
                GamePartController.setAttacker(-5, game);
                GamePartController.setTarget(-5, game);
                GamePartController.setAllianceAttacker(null, game);
                GamePartController.setAllianceOfTarget(null, game);
            }


        }
        response = new MouseClickResponse(clicked);
        Server.getDataBaseHandler().save(player);
        return response;
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

    public boolean isLock() {
        return isLock;
    }

    public void setLock(boolean lock) {
        isLock = lock;
    }
}
