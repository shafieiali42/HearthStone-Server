package controller.request;

import Models.Cards.CardClasses.Cards;
import controller.response.Response;
import controller.response.SetAllLittleCardsPanelsResponse;

import java.util.HashMap;

public class SetAllLittleCardsPanelsRequest extends Request {


    private String userName;

    public SetAllLittleCardsPanelsRequest(String sendersToken,String userName) {
        this.userName = userName;
    }

    @Override
    public Response execute() {
        HashMap<String, Integer> allCardsNameMane = new HashMap<>();
        for (int i = 0; i < Cards.getAllCards().size(); i++) {
            allCardsNameMane.put(Cards.getAllCards().get(i).getName(), Cards.getAllCards().get(i).getManaCost());
        }
        Response response = new SetAllLittleCardsPanelsResponse(allCardsNameMane);
        return response;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
