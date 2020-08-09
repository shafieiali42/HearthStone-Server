package controller.response;

import java.util.ArrayList;

public class ShowRankResponse extends Response {

    private ArrayList<String> nameOfPlayers;
    private ArrayList<String> numOfCups;


    public ShowRankResponse(ArrayList<String> nameOfPlayers, ArrayList<String> numOfCups) {
        this.nameOfPlayers = nameOfPlayers;
        this.numOfCups = numOfCups;
    }

    public ArrayList<String> getNameOfPlayers() {
        return nameOfPlayers;
    }

    public void setNameOfPlayers(ArrayList<String> nameOfPlayers) {
        this.nameOfPlayers = nameOfPlayers;
    }

    public ArrayList<String> getNumOfCups() {
        return numOfCups;
    }

    public void setNumOfCups(ArrayList<String> numOfCups) {
        this.numOfCups = numOfCups;
    }
}
