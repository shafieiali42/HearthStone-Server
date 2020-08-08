package controller.response;


import java.util.ArrayList;

public class ShowDeckButtonsResponse extends Response {


    private ArrayList<String> deckNamesList;

    public ShowDeckButtonsResponse(ArrayList<String> deckNamesList) {
        setResponseType("ShowDeckButtonsResponse");
        this.deckNamesList = deckNamesList;
    }




    public ArrayList<String> getDeckNamesList() {
        return deckNamesList;
    }


    public void setDeckNamesList(ArrayList<String> deckNamesList) {
        this.deckNamesList = deckNamesList;
    }
}
