package controller.request;

import Models.Player.Player;
import controller.Status;
import controller.response.GoToPageResponse;
import controller.response.Response;
import database.DataBase;

public class GoToPageRequest extends Request {


    private String pageName;
    private String userName;


    public GoToPageRequest(String sendersToken,String userName, String pageName) {
        setRequestType("GoToPageRequest");
        setRequestSendersToken(sendersToken);
        this.pageName = pageName;
        this.userName=userName;
    }



    @Override
    public Response execute() {
        Player player = DataBase.fetchPlayer(userName);

        if (player.getPlayerStatusInGame().equals(Status.BUY_PAGE_FROM_COLLECTION)) {
            pageName="CollectionPage";
        } else {
            pageName="MainMenuPage";
        }
        Response response =new GoToPageResponse(pageName);
        return response;
    }


    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
