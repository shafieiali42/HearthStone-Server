package controller.request;

import Models.Player.Player;
import controller.Status;
import controller.response.GoToPageResponse;
import controller.response.Response;
import server.Server;

import javax.swing.*;

public class GoBackFromCollectionPages extends Request {





    public GoBackFromCollectionPages(String userName) {
        setUserName(userName);
        setRequestType("GoBackFromCollectionPages");

    }


    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(getUserName());
        Response response = null;
        if (player.getPlayerStatusInGame().equals(Status.COLLECTIONS_PAGE)) {
            response = new GoToPageResponse("MainMenuPageNormal");
//            ClientMain.getMyMainFrame().setContentPane(new MainMenuPage());
        } else if (player.getPlayerStatusInGame().equals(Status.MAKE_DECK) ||
                player.getPlayerStatusInGame().equals(Status.CHANGE_DECK)) {
            JOptionPane.showMessageDialog(null, "You should press done button", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (player.getPlayerStatusInGame().equals(Status.COLLECTION_PAGE_FROM_PLAY)) {
            if (player.getCurrentDeck().getHero() != null) {
                player.setPlayerStatusInGame(Status.PLAY_PAGE);
                response = new GoToPageResponse("GamePage");
//                ClientMain.getMyMainFrame().setContentPane(GamePage.getInstance());
            } else {
                player.setPlayerStatusInGame(Status.MAIN_MENU_PAGE);
                response = new GoToPageResponse("MainMenuPageNormal");
//                ClientMain.getMyMainFrame().setContentPane(new MainMenuPage());
            }
        }
        Server.getDataBaseHandler().save(player);
        return response;
    }


}
