package controller.request;

import controller.controllers.Administer;
import controller.response.Response;
import controller.response.ShowRankResponse;

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
        } else if (typeOfRank.equalsIgnoreCase("Top10")) {
            response =new ShowRankResponse(Administer.giveBestPlayers("name"),
                    Administer.giveBestPlayers("cup"),typeOfRank,"");
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
