package controller.request;

import Logic.PlayLogic.Game;
import Models.Player.InGamePlayer;
import Models.Player.Player;
import com.google.gson.Gson;
import controller.Status;
import controller.controllers.GamePartController;
import controller.response.*;
import server.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class MouseReleasedRequest extends Request {


    private String cardName;
    private String xCoordinateOfReleased;
    private String yCoordinateOfReleased;

    public MouseReleasedRequest(String sendersToken, String userName, String cardName,
                                String xCoordinateOfReleased, String yCoordinateOfReleased) {
        setUserName(userName);
        setRequestType("MouseReleasedRequest");
        this.cardName = cardName;
        this.xCoordinateOfReleased = xCoordinateOfReleased;
        this.yCoordinateOfReleased = yCoordinateOfReleased;
    }


    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(getUserName());
        Game game = Server.giveGameWithPlayer(getUserName());
        InGamePlayer whitePlayer = Server.giveInGamePlayer(getUserName());
        Response response = null;
        String message = null;

        PrintWriter printer = null;
        try {
            printer = new PrintWriter(Server.giveSocketWithUserName(whitePlayer.getPlayer().getUserName()).getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (player.getPlayerStatusInGame().equals(Status.PLAY_PAGE_MY_TURN) ||
                player.getPlayerStatusInGame().equals(Status.PLAY_PAGE)) {

            if (!GamePartController.checkThatCanReleaseCard(Integer.parseInt(xCoordinateOfReleased),
                    Integer.parseInt(yCoordinateOfReleased),game)){
                response = new ShowJOptionPaneResponse("Its Not Your Turn:))");
                return response;
            }

            game.getMapper().setAddedBeforeForBeingBetween(false);

            if (!Objects.requireNonNull(GamePartController.getTypeOfGivenCard(this.cardName)).
                    equalsIgnoreCase("minion")) {

                GamePartController.setPlayingCardOfGameState(this.cardName, game);
                message = GamePartController.playCard(game, 7);
//                dragging = false;
                response = new PlayCardResponse(message, cardName);
                Response secondResponse = new ShowPlayPanelRequest(getUserName(), "").execute();
                String responseMessage = new Gson().toJson(secondResponse);
                printer.println(secondResponse.getResponseReceiversToken());
                printer.println(secondResponse.getResponseType());
                printer.println(responseMessage);
                printer.flush();
//                response=new ShowPlayPanelRequest(userName,"").execute();
            } else if (GamePartController.canAddMinionToBattleGround(game)) {


                if (Integer.parseInt(xCoordinateOfReleased) < 50) {
                    GamePartController.setPlayingCardOfGameState(this.cardName, game);
                    message = GamePartController.playCard(game, 1);
                    response = new PlayCardResponse(message, cardName);
                } else if (Integer.parseInt(xCoordinateOfReleased) > 125 && Integer.parseInt(xCoordinateOfReleased) < 220) {
                    GamePartController.setPlayingCardOfGameState(this.cardName, game);
                    message = GamePartController.playCard(game, 2);
                    response = new PlayCardResponse(message, cardName);
                } else if (Integer.parseInt(xCoordinateOfReleased) > 265 && Integer.parseInt(xCoordinateOfReleased) < 365) {
                    GamePartController.setPlayingCardOfGameState(this.cardName, game);
                    message = GamePartController.playCard(game, 3);
                    response = new PlayCardResponse(message, cardName);
                } else if (Integer.parseInt(xCoordinateOfReleased) > 410 && Integer.parseInt(xCoordinateOfReleased) < 510) {
                    GamePartController.setPlayingCardOfGameState(this.cardName, game);
                    message = GamePartController.playCard(game, 4);
                    response = new PlayCardResponse(message, cardName);
                } else if (Integer.parseInt(xCoordinateOfReleased) > 555 && Integer.parseInt(xCoordinateOfReleased) < 655) {
                    GamePartController.setPlayingCardOfGameState(this.cardName, game);
                    message = GamePartController.playCard(game, 5);
                    response = new PlayCardResponse(message, cardName);
                } else if (Integer.parseInt(xCoordinateOfReleased) > 700 && Integer.parseInt(xCoordinateOfReleased) < 800) {
                    GamePartController.setPlayingCardOfGameState(this.cardName, game);
                    message = GamePartController.playCard(game, 6);
                    response = new PlayCardResponse(message, cardName);

                }
                if (!game.getMapper().isAddedBeforeForBeingBetween()) {
                    GamePartController.setPlayingCardOfGameState(this.cardName, game);
                    message = GamePartController.playCard(game, 7);
                    response = new PlayCardResponse(message, cardName);

                }

                Response secondResponse = new ShowPlayPanelRequest(getUserName(), "").execute();
                String responseMessage = new Gson().toJson(secondResponse);
                printer.println(secondResponse.getResponseReceiversToken());
                printer.println(secondResponse.getResponseType());
                printer.println(responseMessage);
                printer.flush();
//                response=new ShowPlayPanelRequest(userName,"").execute();
//                dragging = false;
            } else {
                response = new ShowJOptionPaneResponse("It's illegal to have more than 7 cards in the battleGround.");
            }
        }
        Server.getDataBaseHandler().save(player);
        return response;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getxCoordinateOfReleased() {
        return xCoordinateOfReleased;
    }

    public void setxCoordinateOfReleased(String xCoordinateOfReleased) {
        this.xCoordinateOfReleased = xCoordinateOfReleased;
    }

    public String getyCoordinateOfReleased() {
        return yCoordinateOfReleased;
    }

    public void setyCoordinateOfReleased(String yCoordinateOfReleased) {
        this.yCoordinateOfReleased = yCoordinateOfReleased;
    }
}
