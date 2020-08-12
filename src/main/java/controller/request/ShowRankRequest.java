package controller.request;

import controller.controllers.Administer;
import controller.response.Response;
import controller.response.ShowRankResponse;
import server.Server;
import utility.Log.Log;

public class ShowRankRequest extends Request {


    private String typeOfRank;

    public ShowRankRequest(String userName, String typeOfRank) {
        setRequestType("ShowRankRequest");
        setUserName(userName);
        this.typeOfRank = typeOfRank;
    }


    @Override
    public Response execute() {
        Response response = null;
        if (typeOfRank.equalsIgnoreCase("MyRank")) {
            response =new ShowRankResponse(Administer.givePlayersAroundMe(getUserName(),"name"),
                    Administer.givePlayersAroundMe(getUserName(),"cup"),typeOfRank,Administer.giveMyRank(getUserName()));
            Log log =new Log(getUserName(),"SeeMyRank");
            Server.getDataBaseHandler().save(log);
        } else if (typeOfRank.equalsIgnoreCase("Top10")) {
            response =new ShowRankResponse(Administer.giveBestPlayers("name"),
                    Administer.giveBestPlayers("cup"),typeOfRank,"");
            Log log =new Log(getUserName(),"SeeTop10");
            Server.getDataBaseHandler().save(log);
        }
        return response;
    }

    public String getTypeOfRank() {
        return typeOfRank;
    }

    public void setTypeOfRank(String typeOfRank) {
        this.typeOfRank = typeOfRank;
    }
}
