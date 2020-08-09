package controller.request;

import Models.Player.InGamePlayer;
import Models.Player.Player;
import controller.response.Response;
import controller.response.ShowChatResponse;
import server.Server;

public class SendTextRequest extends Request {


    private String text;


    public SendTextRequest(String userName, String text) {
        setRequestType("SendTextRequest");
        setUserName(userName);
        this.text = text;
    }


    @Override
    public Response execute() {
        Player player= Server.getDataBaseHandler().fetchPlayer(getUserName());
        InGamePlayer inGamePlayer=Server.giveAnotherPlayer(getUserName());
        Response response=new ShowChatResponse(text);
        Server.sendResponse(inGamePlayer.getPlayer().getUserName(),response);
        return response;
    }



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
