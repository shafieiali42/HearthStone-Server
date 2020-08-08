package controller.request;

import Models.Player.Player;
import controller.Status;
import controller.response.GoToPageResponse;
import controller.response.Response;
import server.Server;

public class GoToPageRequest extends Request {


    private String pageName;



    public GoToPageRequest(String sendersToken,String userName, String pageName) {
        setUserName(userName);
        setRequestType("GoToPageRequest");
        setRequestSendersToken(sendersToken);
        this.pageName = pageName;

    }



    @Override
    public Response execute() {
        Player player = Server.getDataBaseHandler().fetchPlayer(getUserName());

        if (player.getPlayerStatusInGame().equals(Status.BUY_PAGE_FROM_COLLECTION)) {
            pageName="CollectionPage";
        } else {
            pageName="MainMenuPage";
            player.setPlayerStatusInGame(Status.MAIN_MENU_PAGE);
        }

        if (pageName.equalsIgnoreCase("CollectionPage")){
            player.setPlayerStatusInGame(Status.COLLECTIONS_PAGE);
        }else if (pageName.equalsIgnoreCase("ShopPage")){
            player.setPlayerStatusInGame(Status.SHOP_PAGE);
        }else if(pageName.equalsIgnoreCase("GamePage")){
            player.setPlayerStatusInGame(Status.PLAY_PAGE);
        }

        Server.getDataBaseHandler().save(player);
        Response response =new GoToPageResponse(pageName);
        return response;
    }


    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }


}
